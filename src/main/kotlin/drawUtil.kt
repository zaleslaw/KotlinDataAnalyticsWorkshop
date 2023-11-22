import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject
import org.jetbrains.kotlinx.dl.api.inference.posedetection.MultiPoseDetectionResult
import java.awt.*
import java.awt.geom.Ellipse2D
import java.awt.geom.Line2D
import java.awt.geom.Rectangle2D
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

fun drawRectanglesForDetectedObjects(
    bufferedImage: BufferedImage,
    detectedObjects: List<DetectedObject>
): BufferedImage {
    val newGraphics = bufferedImage.createGraphics()
    newGraphics.drawImage(bufferedImage, 0, 0, null)
    newGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    newGraphics as Graphics2D

    val width = bufferedImage.width
    val height = bufferedImage.height
    val labelColor: Color = Color.WHITE
    newGraphics.font = newGraphics.font.deriveFont(Font.BOLD).deriveFont(newGraphics.font.getSize() * 8F)

    detectedObjects.forEach {
        val x = it.xMin * width
        val y = it.yMin * height

        val frameWidth = 20f * it.probability

        val stroke1: Stroke = BasicStroke(frameWidth)
        when (it.label) {
            "person" -> newGraphics.color = Color.WHITE
            "bicycle" -> newGraphics.color = Color.BLUE
            "car" -> newGraphics.color = Color.GREEN
            "traffic light" -> newGraphics.color = Color.ORANGE
            "train" -> newGraphics.color = Color.PINK

            else -> newGraphics.color = Color.MAGENTA
        }
        newGraphics.stroke = stroke1

        newGraphics.draw(Rectangle2D.Float(x, y, it.xMax * width - x, it.yMax * height - y))

        if (it.label != null) {
            val label = "${it.label} : " + "%.2f".format(it.probability)
            newGraphics.color = labelColor
            newGraphics.drawString(label, x, y - newGraphics.fontMetrics.maxDescent - frameWidth / 2)
        }
    }
    ImageIO.write(bufferedImage, "jpg", File("../serverFiles/image4.jpg"))
    newGraphics.dispose()
    return bufferedImage
}

fun drawDetectedPose(
    bufferedImage: BufferedImage,
    result: MultiPoseDetectionResult
): BufferedImage {
    val newGraphics = bufferedImage.createGraphics()
    newGraphics.drawImage(bufferedImage, 0, 0, null)
    newGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    newGraphics as Graphics2D

    val width = bufferedImage.width
    val height = bufferedImage.height

    val landmarkColor: Color = Color.WHITE
    val edgeColor: Color = Color.ORANGE

    result.poses.forEach {
        newGraphics.stroke = BasicStroke(6f)
        newGraphics.color = edgeColor
        val detectedPose = it.second

        detectedPose.edges.forEach { (start, end, _, _) ->
            newGraphics.draw(
                Line2D.Float(
                    width * start.x, height * start.y,
                    width * end.x, height * end.y
                )
            )
        }

        val r = 5.0f
        newGraphics.color = landmarkColor
        detectedPose.landmarks.forEach { (x, y, _, _) ->
            newGraphics.fill(Ellipse2D.Float(width * x - r, height * y - r, 2 * r, 2 * r))
        }
    }



    ImageIO.write(bufferedImage, "jpg", File("../serverFiles/detectedPose.jpg"))
    newGraphics.dispose()
    return bufferedImage
}
