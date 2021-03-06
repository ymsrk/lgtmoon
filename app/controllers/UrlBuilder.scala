package controllers

import com.google.inject.Inject
import play.api.Configuration

class UrlBuilder @Inject() (
  config: Configuration
){
  val base = config.get[String]("image.baseUrl")

  def image(id: Int): String = base + "/" +  id

  def images(ids: Seq[Int]): Seq[String] = ids.map(id => image(id))
}
