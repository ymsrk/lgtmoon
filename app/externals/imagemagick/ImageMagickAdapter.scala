package externals.imagemagick

import java.io._
import play.api.Play
import org.im4java.core._

/** imagemagickを利用するためのAdapter */
class ImageMagickAdapter() {
  /** imagemagickのバイナリのディレクトリ */
  val imagemagickDir = Play.current.configuration.getString("imagemagick.dir").get
  /** フォントファイルがあるディレクトリ */
  val fontDir = Play.current.configuration.getString("imagemagick.fontDir").get

  /**
   * 画像の変換を行う
   *
   * @param beforePath: String 生成前の画像のPATH
   * @param afterPath: String 生成後の画像のPATH
   */
  def convert(
    beforePath: String,
    afterPath: String) {
    val operation = new IMOperation()
    operation.addImage(beforePath)
    operation.geometry(480, 480)
    // LGTMの文字
    operation.gravity("center")
    operation.font(font("Aileron-Black.otf"))
    operation.pointsize(96)
    operation.stroke("none")
    operation.fill("white")
    operation.kerning(16);
    operation.strokewidth(5);
    operation.annotate(0, 0, 0, 0, "LGTM")
    // Looks Good To Me の文字
    operation.font(font("Aileron-Light.otf"))
    operation.pointsize(12)
    operation.kerning(10);
    operation.annotate(0, 0, 0, 64, "Looks  Good  To  Me")
    operation.addImage(afterPath)
    // コマンド実行
    val command = new ConvertCmd()
    command.setSearchPath(imagemagickDir)
    command.run(operation)
  }

  /**
   * imagemagickに渡すべきフォントのパスを取得する
   *
   * @param fontName: String e.g. "Aileron-Regular.otf"
   * @return String ImageMagickに渡すべきフォントのパス
   */
  def font(fontName: String): String = {
    fontDir + "/" + fontName
  }

}