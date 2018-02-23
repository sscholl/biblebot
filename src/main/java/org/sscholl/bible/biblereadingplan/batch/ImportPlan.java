package org.sscholl.bible.biblereadingplan.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.sscholl.bible.biblereadingplan.batch.dto.PlanDayCsvEntry;
import org.sscholl.bible.biblereadingplan.model.Passage;
import org.sscholl.bible.biblereadingplan.model.Plan;
import org.sscholl.bible.biblereadingplan.model.PlanDay;
import org.sscholl.bible.biblereadingplan.repository.PlanDayRepository;
import org.sscholl.bible.biblereadingplan.repository.PlanRepository;
import org.sscholl.bible.biblereadingplan.service.ScheduleService;

import javax.persistence.EntityManagerFactory;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class ImportPlan {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportPlan.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    PlanDayRepository planDayRepository;

    Plan plan;

    @Bean
    public FlatFileItemReader<PlanDayCsvEntry> reader() {
        FlatFileItemReader<PlanDayCsvEntry> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("plan.csv"));
        reader.setEncoding(StandardCharsets.UTF_8.name());
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<PlanDayCsvEntry>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter("|");
                setNames(new String[]{"input", "date", "dayNumber", "text", "passage1", "passage2", "passage3", "passage4", "passage5", "passage6", "passage7", "passage8", "passage9"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<PlanDayCsvEntry>() {{
                setTargetType(PlanDayCsvEntry.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<PlanDayCsvEntry, PlanDay> processor() {
        class processor implements ItemProcessor<PlanDayCsvEntry, PlanDay> {

            @Override
            public PlanDay process(PlanDayCsvEntry planDayCsvEntry) throws Exception {
                if (plan == null) {
                    throw new Exception("no plan available");
                }

                PlanDay planDay;
                int index = planDayCsvEntry.getDayNumber() - 1;
                if (plan.getDays().size() <= index) {
                    Assert.isTrue(plan.getDays().size() == index, "dayNumber must increment value");
                    planDay = new PlanDay();
                    planDay.setPlan(plan);
                    plan.getDays().add(index, planDay);
                } else {
                    planDay = plan.getDays().get(index);
                }
                planDay.setFree(false);
                if (planDayCsvEntry.getPassage1().isEmpty()) {
                    planDay.setFree(true);
                }
                planDay.setText(planDayCsvEntry.getText());

                planDay.setPassages(new LinkedList<>());

                List<String> passages = new LinkedList<>();
                passages.add(planDayCsvEntry.getPassage1());
                passages.add(planDayCsvEntry.getPassage2());
                passages.add(planDayCsvEntry.getPassage3());
                passages.add(planDayCsvEntry.getPassage4());
                passages.add(planDayCsvEntry.getPassage5());
                passages.add(planDayCsvEntry.getPassage6());
                passages.add(planDayCsvEntry.getPassage7());
                passages.add(planDayCsvEntry.getPassage8());
                passages.add(planDayCsvEntry.getPassage9());
                for (String p : passages) {
                    if (p != null && !p.isEmpty()) {
                        Passage passage = new Passage();
                        passage.setTitle(p);
                        passage.setDay(planDay);
                        planDay.getPassages().add(passage);
                    }
                }
                scheduleService.setDefaultValues(planDay);
                return planDay;
            }
        }
        return new processor();
    }

    @Bean
    public JpaItemWriter<PlanDay> writer() {
        JpaItemWriter<PlanDay> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Job importPlanJob() {
        return jobBuilderFactory.get("importPlanJob")
                .incrementer(new RunIdIncrementer())
                .start(retrievePlanStep())
                .next(importPlanDaysStep())
                .next(savePlanStep())
                .build();
    }

    @Bean
    public Step retrievePlanStep() {
        return stepBuilderFactory.get("retrievePlanStep").tasklet((contribution, chunkContext) -> {
            LOGGER.debug("retrievePlanStep");
            plan = planRepository.findAll().get(0);
            if (plan.getDays() == null) {
                plan.setDays(new LinkedList<>());
            }
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step importPlanDaysStep() {
        return stepBuilderFactory.get("importPlanDaysStep")
                .<PlanDayCsvEntry, PlanDay>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(items -> {/* do nothing save in savePlanStep */})
//                .writer(items -> items.forEach(i -> planDayRepository.saveAndFlush(i)))
                .build();
    }

    @Bean
    public Step savePlanStep() {
        return stepBuilderFactory.get("savePlanStep").tasklet((contribution, chunkContext) -> {
            planRepository.saveAndFlush(plan);
            return RepeatStatus.FINISHED;
        }).build();
    }

}
