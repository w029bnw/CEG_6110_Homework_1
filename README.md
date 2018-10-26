# CEG 6110 Homework 1 
[![Build Status](https://travis-ci.com/w029bnw/CEG_6110_Homework_1.svg?branch=master)](https://travis-ci.com/w029bnw/CEG_6110_Homework_1)

Homework #1 Assignment for CEG 6110

This android app was designed and created for Homework Assignment #1 for CEG 6110 using Android Studio. It has two interactive User Interfaces
(UIs), as listed in the assignment requirements. The first UI allows the user to type any set of words into a text field, which can be
randomly set to any color by clicking a button. The second UI allows the user to draw a picture in a view and select any color with which
to draw with. This view can then be cleared or saved to the user's device as a PNG. The user is able to toggle between UIs by using arrow
buttons found at the bottom of each respective page.

## Getting Started

  1. Download .zip folder from GitHub
  2. In Android Studio, import a new project ```File->New->Import Project```
  3. Select the zipped folder and click ```Next->Finish```

### Prerequisites

This app requires an Android environment of at least Android 6.0 Marshmallow on the targeted device.

### Installing

* Running in Android Studio

  1. Import the project into Android Studio

  2. Connect Android device to computer via USB cable

  3. Enable USB debugging in the Developer Options as follows:

      a. Open the Settings app      
      b. Select System (Android 8.0 or higher)      
      c. Scroll to the bottom and select About Phone      
      d. Scroll to the bottom and tap Build Number 7 times      
      e. Return to the previous screen to find Developer Options near the bottom      
      f. Open Developer Options, then scroll down to enable USB debugging

  4. In Android Studio, click the app module in the Project window and select Run in the toolbar

  5. Select your device in the Deployment Target window and click OK


* Running on Android Device

  1. Download and unzip project folder from GitHub

  2. Connect Android device to computer via USB cable and enable file shari

  3. Copy the release APK from the release folder within the root directory of
  the project into a desired folder on the Android Device

  4. Using the Android Device, click the APK file and give the app permissions
  to install

## Deployment

Once installed onto the Android Device, the Homework1 app can be deployed through the device's app menu by clicking on its icon.

### Note

If issues are experienced during installation, make sure that the Android device is able to install apps from Unknown Sources.
This setting can be changed at ```Settings->Security``` and checking the option under Device Administration.

### UI #1: Colored Text

This UI contains a textbox that can be edited by the user to contain any series of text. The text inside of the textbox can be modified
to any random color by clicking the "Change Color" button. Once the color changes, the color's RGB and HTML Hex values will be displayed
between the textbox and the button.


<img src="https://github.com/w029bnw/CEG_6110_Homework_1/blob/master/Homework1%20Photos/Screenshot_20180916-151631.png" width="256" height="412" title="Colored Text Screen1"> <img src="https://github.com/w029bnw/CEG_6110_Homework_1/blob/master/Homework1%20Photos/Screenshot_20180916-151706.png" width="256" height="412" title="Colored Text Screen2">


The user can switch pages to the next UI by clicking the arrow button located in the bottom right of the screen.

### UI #2: Drawing View

This UI contains a drawing view, located directly in the middle of the screen, where the user is able to draw using the touch screen on
their device. Above the drawing view on the left is a button with the image of a small trashcan that can be used to clear any marks inside
of the drawing view. If the image is cleared, the user can undo the action by using the button located below the drawing view on the
left labeled "UNDO". Alternatively, if the user wants to redo any action previously undone, the user can click the button located below the 
drawing view on the right labeled "REDO". 


<img src="https://github.com/w029bnw/CEG_6110_Homework_1/blob/master/Homework1%20Photos/Screenshot_20180916-151730.png" width="256" height="412" title="Drawing View Screen1"> <img src="https://github.com/w029bnw/CEG_6110_Homework_1/blob/master/Homework1%20Photos/Screenshot_20180916-152005.png" width="256" height="412" title="Drawing View Screen1">


Located at the bottom of the screen is a floating button labeled "Brush Color". This button can be selected to deploy a dialog box where the user
is able to select a desired color using RGB sliders. Once the color is selected, the dialog is dismissed and the brush color within
the drawing view is set.


<img src="https://github.com/w029bnw/CEG_6110_Homework_1/blob/master/Homework1%20Photos/Screenshot_20180916-165752.png" width="256" height="412" title="Drawing View Screen3">


Above the drawing view on the right is a button with the image of a small floppy disk that can be
used to save the image drawn by the user. This is completed by compressing the bitmap of the drawing view and exporting it to the device's
pictures. If the app has not previously received permissions to create a directory for the images to be saved to, it will request permissions
from the user. 


<img src="https://github.com/w029bnw/CEG_6110_Homework_1/blob/master/Homework1%20Photos/34520441-82ce-4d98-85cc-117109205237.png" width="256" height="412" title="Drawing View Screen4">


If the user wishes to return to the first UI of the app, they can click the small arrow button located at the bottom of the page.

## Design

The only real design decision that was made was surrounding the ability to switch between UI pages. A ViewPager was initially used to
switch between UI pages but this was layered on top of each of the views, rendering any buttons or views useless. It was eventually
decided that each view would then be set manually and the ability to switch between UI pages would be implemented with buttons. This met
the requirement for easy switching between pages for the user and allowed interaction with the components on each page by the user.

Classes were picked for their easy implementation and inclusion in the Android Studio project. The Drawing View library had a strong
majority of the desired functionality (clear,undo,redo,save). The Android Material Color Picker Dialog library was implemented to meet
the final criteria of choosing colors. The dialog portion of this particular class made it easy to use since it required less space, and
it allowed for more options by using RGB sliders.

Other than this, the app requirements were rather straight forward. No additional classes were created, as they were not necessary, and the direction of the design steered towards meeting all of the criteria of the assignment.

## Built With

* [Android Studio](https://developer.android.com/studio/)

## Authors

* **Brittany Woods** - *Initial work* - [w029bnw](https://github.com/w029bnw)

## Acknowledgments

Two external libraries were used in the making of this app:
  * DrawingView by Raed Mughaus : https://github.com/Raed-Mughaus/DrawingView
  * Android Material Color Picker Dialog by Pes8: https://github.com/Pes8/android-material-color-picker-dialog
