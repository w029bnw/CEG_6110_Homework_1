# CEG_6110_Homework_1
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

The app requires an Android environment to run in.

### Installing

  1. Connect Android device to computer via USB cable
  2. Enable USB debugging in the Developer Options as follows:
  
      a. Open the Settings app    
      b. Select System (Android 8.0 or higher)    
      c. Scroll to the bottom and select About Phone    
      d. Scroll to the bottom and tap Build Number 7 times    
      e. Return to the previous screen to find Developer Options near the bottom    
      f. Open Developer Options, then scroll down to enable USB debugging
    
  3. In Android Studio, click the app module in the Project window and select Run in the toolbar
  4. Select your device in the Deployment Target window and click OK
  
## Deployment

Once installed onto the Android Device, the Homework1 app can be deployed through the device's app menu by clicking on its icon.

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

## Built With

* [Android Studio](https://developer.android.com/studio/)

## Authors

* **Brittany Woods** - *Initial work* - [w029bnw](https://github.com/w029bnw)

## Acknowledgments

Two external libraries were used in the making of this app:
  * DrawingView by Raed Mughaus : https://github.com/Raed-Mughaus/DrawingView 
  * Android Material Color Picker Dialog by Pes8: https://github.com/Pes8/android-material-color-picker-dialog
