import play.api._


trait MainModule extends MacwireControllerCache {
  import com.softwaremill.macwire.MacwireMacros._

  lazy val companyList: models.CompanyList = models.Company

  // Note: controllers must not be lazy to be saved in cache
  val appController = registerController(wire[controllers.Application])
}


// We can override the default settings freely
trait TestModule extends MainModule {

  override lazy val companyList = new models.CompanyList() {
    def options = List(("1", "test-company-1"),
                       ("2", "test-company-2"))
  }
}


// MacwirePlayControllerWiring is responsible for controller creation
object Global extends MacwirePlayControllerWiring {

  // We have freedom in how we set up the application
  // - in this example we simply use a system property
  val module = if (System.getProperty("macwire-test-mode", "no") == "yes")
    new TestModule() {}
  else 
    new MainModule() {} 

}
