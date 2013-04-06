import play.api._

trait MacwirePlayControllerWiring extends GlobalSettings {

  val module: MacwireControllerCache

  // takes controller from cache or resorts to defaults
  override def getControllerInstance[T](clazz: Class[T]) =
    module.macwiredControllers(clazz).map(_.asInstanceOf[T]).getOrElse(
      super.getControllerInstance(clazz))
}

trait MacwireControllerCache {
  import scala.collection.mutable.Map

  private lazy val wireCache = Map[Class[_], Any]()

  def registerController[T](obj: T) = {
    wireCache.put(obj.getClass, obj)
    obj
  }

  val macwiredControllers = wireCache.lift
}

