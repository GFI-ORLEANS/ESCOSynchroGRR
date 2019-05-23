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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchTest {
	
	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;


	@Test
	@Sql("/multiEtablissement_init.sql")
	public void misAjourEtablissement() throws Exception {
 		JobExecution misAjourEtablissement = jobLauncherTestUtils.launchStep("misAjourEtablissement");
		Assert.assertEquals(BatchStatus.COMPLETED, misAjourEtablissement.getStatus());
		Assert.assertEquals(5, misAjourEtablissement.getStepExecutions().iterator().next().getReadCount());
		Assert.assertEquals(5, misAjourEtablissement.getStepExecutions().iterator().next().getWriteCount());
	}

	@Test
	@Sql("/multiEtablissement_init.sql")
	public void misAjourPersonnes() throws Exception {
 		JobExecution misAjourPersonnes = jobLauncherTestUtils.launchStep("misAjourPersonnes");
		Assert.assertEquals(BatchStatus.COMPLETED, misAjourPersonnes.getStatus());
		Assert.assertEquals(9, misAjourPersonnes.getStepExecutions().iterator().next().getReadCount());
		Assert.assertEquals(7, misAjourPersonnes.getStepExecutions().iterator().next().getWriteCount());
	}

	@Test
	@Sql("/multiEtablissement_init.sql")
	public void suppressionComptesAbsentsLDAP() throws Exception {

		JobExecution suppressionComptesAbsentsLDAP = jobLauncherTestUtils.launchStep("suppressionComptesAbsentsLDAP");
		Assert.assertEquals(BatchStatus.COMPLETED, suppressionComptesAbsentsLDAP.getStatus());
		Assert.assertEquals(3, suppressionComptesAbsentsLDAP.getStepExecutions().iterator().next().getReadCount());
		Assert.assertEquals(1, suppressionComptesAbsentsLDAP.getStepExecutions().iterator().next().getWriteCount());
	}

	@Test
	@Sql("/multiEtablissement_init.sql")
	public void suppressionReservationAnciennes() throws Exception {
		/*IDatabaseConnection dconnection = new DatabaseConnection(dataSource.getConnection());
		IDataSet dataSet = dconnection.createDataSet();
		FlatXmlDataSet.write(dataSet, new FileOutputStream("full.xml"));
*/
		JobExecution suppressionReservationAnciennes = jobLauncherTestUtils.launchStep("suppressionReservationAnciennes");
		Assert.assertEquals(BatchStatus.COMPLETED, suppressionReservationAnciennes.getStatus());
 		Assert.assertEquals(2, suppressionReservationAnciennes.getStepExecutions().iterator().next().getWriteCount());
	}

	@Test
	@Sql("/multiEtablissement_init.sql")
	public void nettoyageLog() throws Exception {

		JobExecution nettoyageLog = jobLauncherTestUtils.launchStep("nettoyageLog");
		Assert.assertEquals(BatchStatus.COMPLETED, nettoyageLog.getStatus());
		Assert.assertEquals(1, nettoyageLog.getStepExecutions().iterator().next().getWriteCount());

	}


	@Test
	@Sql("/multiEtablissement_init.sql")
	public void endBatch() throws Exception {
		JobExecution endBatch = jobLauncherTestUtils.launchStep("endBatch");
		Assert.assertEquals(BatchStatus.COMPLETED, endBatch.getStatus());
	}

/*	@Test
	@Sql("/multiEtablissement_init.sql")
	public void launchJob() throws Exception {
		JobExecution launchJob = jobLauncherTestUtils.launchJob();
		Assert.assertEquals(BatchStatus.COMPLETED, launchJob.getStatus());
	}*/

}
