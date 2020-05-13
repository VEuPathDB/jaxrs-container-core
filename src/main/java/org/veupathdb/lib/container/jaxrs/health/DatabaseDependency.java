package org.veupathdb.lib.container.jaxrs.health;

import org.apache.logging.log4j.Logger;
import org.gusdb.fgputil.db.pool.DatabaseInstance;

import java.sql.SQLException;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Database Dependency
 *
 * Dependency wrapper for a database instance.
 */
public class DatabaseDependency extends ExternalDependency {
  private static final Logger LOG = getLogger(DatabaseDependency.class);

  private final String url;
  private final int port;
  private final DatabaseInstance ds;

  private String testQuery = "SELECT 1 FROM dual";

  public DatabaseDependency(String name, String url, int port, DatabaseInstance ds) {
    super(name);
    this.ds = ds;
    this.url = url;
    this.port = port;
  }

  @Override
  public TestResult test() {
    LOG.info("Checking dependency health for database {}", name);

    if (!pinger.isReachable(url, port))
      return new TestResult(this, false, Status.UNKNOWN);

    try (
      var con  = ds.getDataSource().getConnection();
      var stmt = con.createStatement()
    ) {
      stmt.execute(testQuery);
      return new TestResult(this, true, Status.ONLINE);
    } catch (SQLException e) {
      LOG.warn("Health check failed for database {}", name);
      LOG.debug(e)
      ;
      return new TestResult(this, true, Status.UNKNOWN);
    }
  }

  @Override
  public void close() throws Exception {
    ds.close();
  }

  public void setTestQuery(String testQuery) {
    this.testQuery = testQuery;
  }
}
