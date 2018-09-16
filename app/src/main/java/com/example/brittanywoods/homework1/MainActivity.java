package com.example.brittanywoods.homework1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.raed.drawingview.DrawingView;
import com.pes.androidmaterialcolorpickerdialog.*;
import com.raed.drawingview.brushes.BrushSettings;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //Variable to request write access
    private static final int REQUEST_WRITE_ACCESS = 0;

    //This is a function that sets the view of the respected UI page and recursively handles button
    //calls to switch pages
    public void switchPages(int val){

        //If on the page with the draw view
        if(val == 0){
            setContentView(R.layout.drawcolor);
            final Button undoButton = (Button)findViewById(R.id.undo);
            final Button redoButton = (Button)findViewById(R.id.redo);
            final FloatingActionButton brushButton = (FloatingActionButton)findViewById(R.id.brushButton);
            final ImageButton saveButton = (ImageButton)findViewById(R.id.save);
            final ImageButton leftButton = (ImageButton)findViewById(R.id.leftArrow);
            final ImageButton clearButton = (ImageButton)findViewById(R.id.clear);
            final DrawingView drawingView = (DrawingView)findViewById(R.id.draw_view);
            final BrushSettings brushSettings = drawingView.getBrushSettings();
            final ColorPicker brushPicker = new ColorPicker(this, 255,255,255);

            drawingView.setUndoAndRedoEnable(true);

            //Pops up the color picker dialog whenever the floating button is clicked
            brushButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(final View view){
                    brushPicker.show();
                }
            });

            brushPicker.enableAutoClose();

            //Once a color is selected, the brush color is changed and the dialog is dismissed
            brushPicker.setCallback(new ColorPickerCallback() {
                @Override
                public void onColorChosen(int color) {
                    brushSettings.setColor(brushPicker.getColor());
                    brushPicker.dismiss();
                }
            });

            //Sets up the draw view listener
            drawingView.setOnDrawListener(new DrawingView.OnDrawListener() {
                @Override
                public void onDraw() {
                    undoButton.setEnabled(true);
                    redoButton.setEnabled(false);
                }
            });

            //Clears the draw view
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawingView.clear();
                    undoButton.setEnabled(!drawingView.isUndoStackEmpty());
                    redoButton.setEnabled(!drawingView.isRedoStackEmpty());
                }
            });

            //Saves the draw view
            saveButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    //Requests permissions to create directory for pictures if permissions are not
                    //already set
                    if (ActivityCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_ACCESS);

                    //If permissions are set then we create the directory
                    } else{
                        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                        folder.mkdirs();
                    }

                    //Create a variable to set the draw view to so it can be saved
                    Bitmap bitmap = drawingView.exportDrawing();
                    exportImage(bitmap);
                }
            });

            //Undoes any previous activity in the draw view
            undoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawingView.undo();
                    undoButton.setEnabled(!drawingView.isUndoStackEmpty());
                    redoButton.setEnabled(!drawingView.isRedoStackEmpty());
                }
            });

            //Redoes any undone activity in the draw view
            redoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawingView.redo();
                    undoButton.setEnabled(!drawingView.isUndoStackEmpty());
                    redoButton.setEnabled(!drawingView.isRedoStackEmpty());
                }
            });

            //Moves to the page with the text view by recursive call
            leftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchPages(1);
                }
            });

        //If on the page with the text view
        } else {

            setContentView(R.layout.textcolor);
            ImageButton rightButton = (ImageButton)findViewById(R.id.rightArrow);
            Button button = (Button)findViewById(R.id.button1);
            final EditText editText = (EditText)findViewById(R.id.editText1);
            final TextView textView = (TextView)findViewById(R.id.Color_Values);
            textView.setText("COLOR: 0r, 0g, 0b, #000000");

            //Generates random color for text view
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Random random = new Random();
                    int r = random.nextInt(256);
                    int g = random.nextInt(256);
                    int b = random.nextInt(256);
                    int color = Color.argb(255,r,g,b);
                    editText.setTextColor(color);
                    textView.setText(String.format("COLOR: %dr, %dg, %db, #%X", r,g,b,color));
                }
            });

            //Moves to the page with the draw view by a recursive call
            rightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchPages(0);
                }
            });
        }
    }

    //This is a function that can be used to compress the picture drawn by the user in the draw view
    //to a PNG file
    private void storePicture(File file, Bitmap bitmap) throws Exception {
        if(!file.exists() && !file.createNewFile()){
            throw new Exception("Not able to create " + file.getPath());
        }

        FileOutputStream stream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        stream.flush();
        stream.close();
    }

    //This is a function to export the picture drawn by the user in the draw view to a PNG file
    private void exportImage(Bitmap bitmap){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagePNG = new File(folder, UUID.randomUUID() + ".png");

        try{
            storePicture(imagePNG,bitmap);
        } catch (Exception e){
            e.printStackTrace();
        }

        MediaScannerConnection.scanFile(
                this,
                new String[]{},
                new String[]{"image/png"},
                null);

        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imagePNG)));
    }

    //Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.cap_ic_launcher_round);
        setContentView(R.layout.activity_main);

        //Creates the pages for the different UIs
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Sets the initial page of the app to be the text view page
        setContentView(R.layout.textcolor);

        //If on the page with the text view we want to make all the same calls and initialization
        //as what is completed in the switchPages function
        if(viewPager.getCurrentItem() == 0) {

            ImageButton rightButton = (ImageButton)findViewById(R.id.rightArrow);
            Button button = (Button)findViewById(R.id.button1);
            final EditText editText = (EditText)findViewById(R.id.editText1);
            final TextView textView = (TextView)findViewById(R.id.Color_Values);
            textView.setText("COLOR: 0r,0g,0b, #000000");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Random random = new Random();
                    int r = random.nextInt(256);
                    int g = random.nextInt(256);
                    int b = random.nextInt(256);
                    int color = Color.argb(255,r,g,b);
                    editText.setTextColor(color);
                    textView.setText(String.format("COLOR: %dr,%dg,%db, #%X", r,g,b,color));
                }
            });

            rightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchPages(0);
                }
            });
        }

        //If on the page with the draw view we want to make all the same calls and initializations
        //as completed in the switchPages function.
        else {
            final Button undoButton = (Button)findViewById(R.id.undo);
            final Button redoButton = (Button)findViewById(R.id.redo);
            final FloatingActionButton brushButton = (FloatingActionButton)findViewById(R.id.brushButton);
            final ImageButton saveButton = (ImageButton)findViewById(R.id.save);
            final ImageButton leftButton = (ImageButton)findViewById(R.id.leftArrow);
            final ImageButton clearButton = (ImageButton)findViewById(R.id.clear);
            final DrawingView drawingView = (DrawingView)findViewById(R.id.draw_view);
            final BrushSettings brushSettings = drawingView.getBrushSettings();
            final ColorPicker brushPicker = new ColorPicker(this, 255,255,255);

            drawingView.setUndoAndRedoEnable(true);

            brushButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(final View view){
                    brushPicker.show();
                }
            });

            brushPicker.enableAutoClose();

            brushPicker.setCallback(new ColorPickerCallback() {
                @Override
                public void onColorChosen(int color) {
                    brushSettings.setColor(brushPicker.getColor());
                    brushPicker.dismiss();
                }
            });

            drawingView.setOnDrawListener(new DrawingView.OnDrawListener() {
                @Override
                public void onDraw() {
                    undoButton.setEnabled(true);
                    redoButton.setEnabled(false);
                }
            });

            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawingView.clear();
                    undoButton.setEnabled(!drawingView.isUndoStackEmpty());
                    redoButton.setEnabled(!drawingView.isRedoStackEmpty());
                }
            });

            saveButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_ACCESS);

                    } else{
                        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                        folder.mkdirs();
                    }

                    Bitmap bitmap = drawingView.exportDrawing();
                    exportImage(bitmap);
                }
            });

            undoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawingView.undo();
                    undoButton.setEnabled(!drawingView.isUndoStackEmpty());
                    redoButton.setEnabled(!drawingView.isRedoStackEmpty());
                }
            });

            redoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawingView.redo();
                    undoButton.setEnabled(!drawingView.isUndoStackEmpty());
                    redoButton.setEnabled(!drawingView.isRedoStackEmpty());
                }
            });

            leftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchPages(1);
                }
            });
        }
    }

    //Handler for the permission request to create a directory for pictures drawn by the user in
    //the draw view
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){

        // If permission is granted we make the directory
        if(requestCode == REQUEST_WRITE_ACCESS){
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                folder.mkdirs();

            //Otherwise we don't. So we do nothing
            } else{
                //DO NOTHING
            }
        }
    }
}
