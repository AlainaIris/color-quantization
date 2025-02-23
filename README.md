# Color Quantization
Images are essential to modern day internet usage. Image files can however be quite large and difficult to process
at scale. As such, color quantization can aid in reducing the required memory storage while retaining the same visual
information. This program provides a color quantization algorithm based upon the octree data structure, handling colors
as 3 dimensional points in space wherein the red, green, and blue components are all orthogonal axes. This use of this
tool will be explained first followed by the results from this quantization algorithm.
## Use of Program
This program can be called via the following after compiling driver:

```java Driver [filename] [colors]```

Note that, as currently implemented, the resultant image may use up to n + 7 colors where n is the input. This is due to
a lack of implementation of ophaned point handling for deletion of colors.

## Results
This program achieves its goal of reducing memory usage for images. Tables provide memory benchmarks while images are shown
in the following order: 8 colors, 32 colors, 256 colors, Source image. Image credits are as follows:

- Iceburg: BALAZS GABOR https://unsplash.com/@balazsgabor17
- Woman in White Dress: Bao Menglong https://unsplash.com/@__menglong
- Man in Green Hat: Ben Iwara https://www.instagram.com/1hundredimages/
- Unsplash: Kyle Hinkson https://www.instagram.com/whereiskylenow_/

### Iceburg
| Image | Memory |
| ---------- | ------ |
| source.png | 1.6 MB |
| 8.png      | 136.0 KB |
| 32.png     | 298.7 KB |
| 64.png     | 509.7 KB |
| 128.png    | 735.8 KB |
| 256.png    | 968.1 KB |
| 1024.png   | 1.5 MB |
----------------
### 8C vs 32C vs 256C vs Source
<img src="https://github.com/AlainaIris/color-quantization/blob/main/iceburg/8.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/iceburg/32.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/iceburg/256.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/iceburg/source.png?raw=true" width='200'/>

### Woman in White Dress
| Image | Memory |
| ---------- | ------ |
| source.png | 1.3 MB |
| 8.png      | 117.1 KB |
| 32.png     | 268.2 KB |
| 64.png     | 349.3 KB |
| 128.png    | 456.0 KB |
| 256.png    | 553.6 KB |
| 1024.png   | 860.7 KB |
----------------
### 8C vs 32C vs 256C vs Source
<img src="https://github.com/AlainaIris/color-quantization/blob/main/woman-white-dress/8.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/woman-white-dress/32.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/woman-white-dress/256.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/woman-white-dress/source.png?raw=true" width='200'/>
### Man in Green Hat
| Image | Memory |
| ---------- | ------ |
| source.png | 1.8 MB |
| 8.png      | 130.8 KB |
| 32.png     | 217.7 KB |
| 64.png     | 347.9 KB |
| 128.png    | 671.0 KB |
| 256.png    | 1.0 MB |
| 1024.png   | 1.3 MB |
----------------
### 8C vs 32C vs 256C vs Source
<img src="https://github.com/AlainaIris/color-quantization/blob/main/man-green-hat/8.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/man-green-hat/32.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/man-green-hat/256.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/man-green-hat/source.png?raw=true" width='200'/>

### Unsplash
| Image | Memory |
| ---------- | ------ |
| source.png | 24.6 MB |
| 8.png      | 1.3 MB |
| 32.png     | 3.9 MB |
| 64.png     | 4.7 MB |
| 128.png    | 6.4 MB |
| 256.png    | 7.9 MB |
| 1024.png   | 11.6 MB |
----------------
### 8C vs 32C vs 256C vs Source
<img src="https://github.com/AlainaIris/color-quantization/blob/main/unsplash/8.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/unsplash/32.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/unsplash/256.png?raw=true" width='200'/><img src="https://github.com/AlainaIris/color-quantization/blob/main/unsplash/source.png?raw=true" width='200'/>

## Conclusions
As you can see, we're able to obtain similar to source quantized versions of images which use far less storage. This is not without its
own costs however. There is an uncanny feeling about the way certain colors are arranged. This can be addressed via alterations to the source
outside of quantization, such as dithering. Additionally, one might consider that a RGB based system is less effective, as the visual difference
we observe is not proportional to the change in red green blue values in all cases. Alterations to this algorithm can be made to specific cases,
such as by changing colors to be grayscale by modifying the color logic.
