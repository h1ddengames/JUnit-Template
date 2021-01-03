package tools;

import annotations.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Compares two images and generates an image highlighting the differences.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "12/29/2020",
        currentRevision = 2, lastModified = "12/30/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
public class ImageComparison {
    final static int highlight = new Color(120, 0, 150, 255).getRGB();
    final static int transparent = new Color(0, 0, 0, 0).getRGB();
    final static int black = new Color(0, 0, 0, 255).getRGB();
    final static int white = new Color(255, 255, 255, 255).getRGB();

    public enum OnDifferenceOption {
        ONLY_HIGHLIGHT, ADD_HIGHLIGHT, ADD_DIFFERENCE, SUBTRACT_DIFFERENCE, SELF_SUBTRACT, NONE
    }

    public enum OnSimilarOption {
        HIGHLIGHT, BLACK, WHITE, TRANSPARENT, NONE
    }

    /**
     * Compares two images pixel by pixel for differences then based on input will generate a new image highlighting the differences.
     * Usage:
     * <pre>{@code
     *      ImageIO.write(ImageComparison.getDifferenceBetweenImages(
     *                             ImageIO.read(new File("src/main/resources/expected-image.png")),
     *                             ImageIO.read(new File("src/main/resources/actual-image.png")),
     *                             ImageComparison.OnDifferenceOption.ADD_HIGHLIGHT,
     *                             ImageComparison.OnSimilarOption.NONE,
     *                             ),
     *                     "png",
     *                     new File("src/main/resources/differences.png"));
     * }</pre>
     * @param image1 The expected image.
     * @param image2 The actual image.
     * @param differenceOption What should be done when a pixel is different?
     * @param similarOption What should be done when a pixel is similar?
     * @return A BufferedImage to be saved to file.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static BufferedImage getDifferenceBetweenImages(BufferedImage image1, BufferedImage image2,
                                                           OnDifferenceOption differenceOption, OnSimilarOption similarOption) {
        // Convert images to pixel arrays.
        final int img1Width = image1.getWidth();
        final int img1Height = image1.getHeight();
        final int totalPixels = img1Width * img1Height;

        final int[] pixelArrayForImage1 = image1.getRGB(0, 0, img1Width, img1Height, null, 0, img1Width);
        final int[] pixelArrayForImage2 = image2.getRGB(0, 0, img1Width, img1Height, null, 0, img1Width);

        float difference = 0;
        float percentDifference = 0;

        // Compare image1 to image2, pixel by pixel.
        // If there is a difference in pixels, add a highlight over image1's pixel.
        for (int i = 0; i < pixelArrayForImage1.length; i++) {
            if (pixelArrayForImage1[i] != pixelArrayForImage2[i]) {
                switch (differenceOption) {
                    case ONLY_HIGHLIGHT:
                        pixelArrayForImage1[i] = highlight;
                        break;
                    case ADD_HIGHLIGHT:
                        pixelArrayForImage1[i] |= highlight;
                        break;
                    case ADD_DIFFERENCE:
                        pixelArrayForImage1[i] += pixelArrayForImage2[i];
                        break;
                    case SUBTRACT_DIFFERENCE:
                        pixelArrayForImage1[i] -= pixelArrayForImage2[i];
                        break;
                    case SELF_SUBTRACT:
                        pixelArrayForImage1[i] -= pixelArrayForImage1[i];
                        break;
                    default:
                        break;
                }

                difference++;
            } else {
                switch (similarOption) {
                    case HIGHLIGHT:
                        pixelArrayForImage1[i] += highlight;
                        break;
                    case BLACK:
                        pixelArrayForImage1[i] = black;
                        break;
                    case WHITE:
                        pixelArrayForImage1[i] = white;
                        break;
                    case TRANSPARENT:
                        pixelArrayForImage1[i] = transparent;
                        break;
                    default:
                        break;
                }
            }
        }

        percentDifference = difference / (float) totalPixels;
        System.out.println("Percent Difference: " + (percentDifference * 100));

        // Save image1's pixels to a new BufferedImage, and return it
        BufferedImage out = new BufferedImage(img1Width, img1Height, BufferedImage.TYPE_INT_ARGB);
        out.setRGB(0, 0, img1Width, img1Height, pixelArrayForImage1, 0, img1Width);
        return out;
    }
}
