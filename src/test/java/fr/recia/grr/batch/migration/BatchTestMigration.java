package fr.recia.grr.batch.migration;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles({"test","BatchMigration"})
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "spring.ldap.embedded.port=12346")
public class BatchTestMigration {
	
	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;	
	
	@Test
	@Sql("/multiEtablissement_init.sql")
	public void testMigration() throws Exception {

		JobExecution migrationRoom = jobLauncherTestUtils.launchStep("migrationRoom");
		Assert.assertEquals(BatchStatus.COMPLETED, migrationRoom.getStatus());
		Assert.assertEquals(2, migrationRoom.getStepExecutions().iterator().next().getReadCount());
		Assert.assertEquals(2, migrationRoom.getStepExecutions().iterator().next().getWriteCount());

	}

}
