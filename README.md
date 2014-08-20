polsl-tpdia-event-generator
===========================

Event Generator for petrol station (TPDIA course, Silesian University of Technology)

## EventEmitter program

The program that emits events are written in Node.js and reads the event cumulative function model form BMP (described later) file and geneates number of events in specified time gaps.

Program is generic-written on event bus, but in default implementation runs tcp server on 3005 port (by default) allowing many clients to listen event occurences.

To run program just type in CLI:

```
cd EventEmitter
node index.js
```

Moreover, sample client is provided to listen events:

```
cd EventEmitter
node sample-client.js
```

#### Requirements

* node.js v0.10.15
* Java (JRE 1.7)

## Generate event cumulative function model

### Reading sample event cumulative function model form BMP file

The model expects the black-and-white BMP image with plot that describes the number of
events occured in specified period of time. The BMP image should be black-and-white
and have one black pixel in each column.

The number of columns (image width) defines the granulity of one day (time gaps), for example
the image with width 1440 defines that each column describes the period of one minute.

Image height defines maximum evevnt occurences in specified period of time, for example
1440x100 image defines 0-100 occurnces in each minute in a day.

`run-dump-plot-data-from-bmp.sh` program finds the most-top black pixel in each column and prints out its position on image
for each image column.

The number of columns is same as image width. The position is
bottom-related, that means the position od most black pixel is defined
as offset position from the bottom of image.

#### Example BMP plot

![Example BMP plot](https://raw.githubusercontent.com/athlan/polsl-tpdia-event-generator/master/DumpPlotDataFromBmp/wykres.bmp)

#### Run precompiled program

```
./run-dump-plot-data-from-bmp.sh ./DumpPlotDataFromBmp/wykres.bmp
```

where `./DumpPlotDataFromBmp/wykres.bmp` is path to your plot file.

#### Compile project

```
cd DumpPlotDataFromBmp
gradle clean build
```

#### Read the points from plot saved as BMP file:

```
java -jar build/libs/DumpPlotDataFromBmp.jar plot.bmp
```

#### Requirements

* Java (JRE 1.7, JDK 1.7)
* Gradle
