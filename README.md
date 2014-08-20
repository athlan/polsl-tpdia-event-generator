polsl-tpdia-event-generator
===========================

Event Generator for petrol station (TPDIA course, Silesian University of Technology)

### Reading sample event cumulative function model form BMP file

The model expects the black-and-white BMP image with plot that describes the number of
events occured in specified period of time. The BMP image should be back-and-white
and have one black pixel in each column.

The number of columns (image width) defines the granulity of one day, for example
the image with width 1440 defines that each column describes the period of one minute.

Image height defines maximum evevnt occurences in specified perioid of time, for example
1440x100 image defines 0-100 occurnces in each minute in a day.

`run-dump-plot-data-from-bmp.sh` program finds the most-top black pixel in each column and prints out its position on image
for each image column.

The number of columns is same as image width. The position is
bottom-related, that means the position od most black pixel is defined
as offset position from the bottom of image.

#### Run precompiled program

```
./run-dump-plot-data-from-bmp.sh ./DumpPlotDataFromBmp/wykres.bmp
```

where `./DumpPlotDataFromBmp/wykres.bmp` is path to your plot file.

### Compile project

```
cd DumpPlotDataFromBmp
gradle clean build
```

### Read the points from plot saved as BMP file:

```
java -jar build/libs/DumpPlotDataFromBmp.jar plot.bmp
```
