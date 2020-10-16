package com.example.poojaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_PDF_CODE = 1000;
    private static final int PICK_IMAGE_CODE = 1005;
    EditText amountEt, noteEt, nameEt, upiIdEt;    //private static final String TAG = "MainActivity";
    ArrayList<String> pathHistory;
    int count = 0;
    Button send, upload;String lasttool;
    final int UPI_PAYMENT = 0;
    Button bmalayalam, benglish,readbook, bkannada, bhindi, btelungu, btamil, bname, bstar, btnfile;
    String language, name, star;
    ProgressDialog mProgressDialog;Boolean pdf=false;Boolean image=false;
    ListView lvInternalStorage;
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    int last;
    Dialog myDialog, myDialogPayment;
    private File[] listFile;
    // WebView web;
    File file;
    PDFView pdfView;Uri imageUri;
    ImageView imageView;
    GifImageView flower,bell,chandathiri,karpooram;
    MediaPlayer player;
    Button bclear, bsave,cleartext, bbook, btool, bchandathiri, bkarpooram, bbell, bflower,bsank,bborard,bborard1;
    String lastDirectory,imagepath;
    Uri selectpdf;String selectpdf1;
    EditText nameedit, staredit;
    RelativeLayout parentrelative, realtivesignature ;
    LinearLayout  linearsand,linearpdf,linearlanguage, linearname, linearstar, linearimage, linearstart, linearpop, linearsignature, chandathirigif, karpooramgif, bellgif, flowergif;
    LinearLayout linearupload;

    private static final String TAG = MainActivity.class.getSimpleName();

    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews();


        setContentView(R.layout.activity_main);


      sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);














        readbook = findViewById(R.id.readbook);
        karpooramgif = findViewById(R.id.karpooramgif);
        bellgif = findViewById(R.id.bellgif);
        bborard=findViewById(R.id.bborard);

        bborard1=findViewById(R.id.bborard1);
        linearsand=findViewById(R.id.linearsand);
        flowergif = findViewById(R.id.flowergif);
        bsank = findViewById(R.id.bsank);
        flower=findViewById(R.id.flower);
        cleartext=findViewById(R.id.cleartext);
        karpooram=findViewById(R.id.karpooram);
        bkarpooram = findViewById(R.id.bkarpooram);
        getSupportActionBar().hide();
        chandathiri= findViewById(R.id.chandathiri);
        bflower = findViewById(R.id.bflower);
 bell= findViewById(R.id.bell);

        if (player == null) {
            player = MediaPlayer.create(this, R.raw.audio);
            player.start();
            player.setLooping(true);
        }


        chandathirigif = findViewById(R.id.chandathirigif);
        bchandathiri = findViewById(R.id.bchandathiri);
        parentrelative = findViewById(R.id.parentrelative);
        linearsignature = findViewById(R.id.linearsignature);
        realtivesignature = findViewById(R.id.realtivesignature);
        bclear = findViewById(R.id.bclear);
        bsave = findViewById(R.id.bsave);
        myDialog = new Dialog(this);
        bname = findViewById(R.id.bname);
        myDialogPayment = new Dialog(this);


        bbook = findViewById(R.id.bbook);
        btool = findViewById(R.id.btool);

        btnfile = findViewById(R.id.btnfile);
        linearpop = findViewById(R.id.linearpop);
        linearlanguage = findViewById(R.id.linearlanguage);
        linearname = findViewById(R.id.linearname);
        linearstar = findViewById(R.id.linearstar);
        linearstart = findViewById(R.id.linearstart);
        linearupload = findViewById(R.id.linearupload);
        linearimage = findViewById(R.id.linearimage);
        linearpdf = findViewById(R.id.linearpdf);
        nameedit = findViewById(R.id.nameedit);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        imageView = findViewById(R.id.imgview);
        staredit = findViewById(R.id.staredit);
        bmalayalam = findViewById(R.id.malayalam);
        benglish = findViewById(R.id.english);
        bkannada = findViewById(R.id.kannada);
        bhindi = findViewById(R.id.hindi);
        btelungu = findViewById(R.id.telungu);
        btamil = findViewById(R.id.tamil);
        bbell = findViewById(R.id.bbell);
        bstar = findViewById(R.id.bstar);
        Dexter.withActivity(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new BaseMultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                super.onPermissionsChecked(report);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                super.onPermissionRationaleShouldBeShown(permissions, token);
            }
        }).check();

        lvInternalStorage = (ListView) findViewById(R.id.lvInternalStorage);
        send = findViewById(R.id.send);
        amountEt = findViewById(R.id.amount_et);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkFilePermissions();
        }
        checkSharedPrefrence();
//        bclear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mSignaturePad.clear();
//            }
//        });
//
//        bsave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
//                if (addJpgSignatureToGallery(signatureBitmap)) {
//                    Toast.makeText(MainActivity.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
//                }
//                if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
//                    Toast.makeText(MainActivity.this, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        linearstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearstart.setVisibility(View.GONE);
                linearlanguage.setVisibility(View.VISIBLE);
            }
        });

        bsank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startsank();
            }
        });
        btool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToolPopup();
            }
        });

        bflower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                parentrelative.setVisibility(View.GONE);
                flowergif.setVisibility(View.VISIBLE);



                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojaaaaaaaaaaaa2);
                    flower.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        parentrelative.setVisibility(View.VISIBLE);
                        flowergif.setVisibility(View.GONE);
                    }
                }, 2500);
            }
        });
        bchandathiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentrelative.setVisibility(View.GONE);
                chandathirigif.setVisibility(View.VISIBLE);

                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.pooja1);
                    chandathiri.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        parentrelative.setVisibility(View.VISIBLE);
                        chandathirigif.setVisibility(View.GONE);
                    }
                }, 5000);



            }
        });

        bbell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentrelative.setVisibility(View.GONE);
                bellgif.setVisibility(View.VISIBLE);
                startbell();

                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojabell);
                    bell.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        parentrelative.setVisibility(View.VISIBLE);
                        bellgif.setVisibility(View.GONE);
                    }
                }, 2500);











            }
        });
        bkarpooram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentrelative.setVisibility(View.GONE);
                karpooramgif.setVisibility(View.VISIBLE);


                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojakar2);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        parentrelative.setVisibility(View.VISIBLE);
                        karpooramgif.setVisibility(View.GONE);
                    }
                }, 2500);







            }
        });
        readbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parentrelative.setVisibility(View.GONE);
                karpooramgif.setVisibility(View.VISIBLE);


                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.bookread);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        parentrelative.setVisibility(View.VISIBLE);
                        karpooramgif.setVisibility(View.GONE);
                        start();
                        //parentrelative.setBackground(R.drawable.poojabooks);


                        parentrelative.setVisibility(View.GONE);

                        if(pdf==true){
                            linearpdf.setVisibility(View.VISIBLE);




                          // selectpdf= Uri.parse(selectpdf);

                        Uri pdffile=Uri.parse(selectpdf.toString());


                        pdfView.fromUri(pdffile).password(null).defaultPage(0).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true)
                                .onDraw(new OnDrawListener() {
                                    @Override
                                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                    }
                                }).onDrawAll(new OnDrawListener() {
                            @Override
                            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                            }
                        }).onPageError(new OnPageErrorListener() {
                            @Override
                            public void onPageError(int page, Throwable t) {
                                Toast.makeText(MainActivity.this,"Eror",Toast.LENGTH_SHORT).show();
                            }
                        }).onPageChange(new OnPageChangeListener() {
                            @Override
                            public void onPageChanged(int page, int pageCount) {

                            }
                        }).onTap(new OnTapListener() {
                            @Override
                            public boolean onTap(MotionEvent e) {
                                return true;
                            }
                        }).onRender(new OnRenderListener() {
                            @Override
                            public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                                pdfView.fitToWidth();
                            }
                        }).enableAnnotationRendering(true).invalidPageColor(Color.WHITE).load()
                        ;
                    }
                    else if(image==true){
                            linearimage.setVisibility(View.VISIBLE);
                                    Uri pdffile=Uri.parse(selectpdf.toString());
//
                            toastMessage(imagepath);
                          // Uri imageUri1 = Uri.parse(imagepath);

                           // imageView.setImageURI(Uri.fromFile(new File(imagepath)));
                           // imageView.setImageURI(Uri.parse(new File(imagepath).toString()));
                     //       selectpdf=Uri.parse(new File(imagepath).toString());
                          //  Uri pdffile=Uri.parse(selectpdf.toString());
//
                        ;
                        imageView.setImageURI(pdffile);
//                        File imgFile = new  File(imagepath);
////
//                            if(imgFile.exists()){
//                                toastMessage("1233");
//                                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//                                //ImageView myImage = (ImageView) findViewById(R.id.imageviewTest);
//
//                                imageView.setImageBitmap(myBitmap);
//
//                            }


                    }}
                }, 2000);














            }
        });

        bborard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // linearpdf.setVisibility(View.GONE);
               // parentrelative.setVisibility(View.VISIBLE);
                stop();
                Intent i = new Intent(MainActivity.this,SandScreen.class);
                startActivity(i);




            }
        });

        bborard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
                // linearpdf.setVisibility(View.GONE);
                // parentrelative.setVisibility(View.VISIBLE);
                Intent i = new Intent(MainActivity.this,SandScreen.class);
                startActivity(i);




            }
        });
        benglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language = "english";
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("language", language);
                editor.apply();


                linearlanguage.setVisibility(View.GONE);



                chandathirigif.setVisibility(View.VISIBLE);

                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.newdor);
                    chandathiri.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        parentrelative.setVisibility(View.VISIBLE);
                        chandathirigif.setVisibility(View.GONE);
                        linearname.setVisibility(View.VISIBLE);
                    }
                }, 1500);


            }
        });
        btamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language = "tamil";
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("language", language);
                editor.apply();


                linearlanguage.setVisibility(View.GONE);
                parentrelative.setVisibility(View.VISIBLE);
                linearname.setVisibility(View.VISIBLE);
            }
        });
        bhindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language = "hindi";
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("language", language);
                editor.apply();
                linearlanguage.setVisibility(View.GONE);
                parentrelative.setVisibility(View.VISIBLE);
                linearname.setVisibility(View.VISIBLE);
            }
        });
        bmalayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language = "malayalam";
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("language", language);
                editor.apply();
            }
        });
        btelungu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language = "telungu";
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("language", language);
                editor.apply();
                linearlanguage.setVisibility(View.GONE);
                parentrelative.setVisibility(View.VISIBLE);
                linearname.setVisibility(View.VISIBLE);
            }
        });
        bkannada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language = "kannada";
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("language", language);
                editor.apply();
                linearlanguage.setVisibility(View.GONE);
                parentrelative.setVisibility(View.VISIBLE);
                linearname.setVisibility(View.VISIBLE);
            }
        });


        bname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameedit.getText().toString();

                if (name.matches("")) {
                    toastMessage("Please Enter Name");
                } else {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("name", name);
                    editor.apply();

                    linearname.setVisibility(View.GONE);
                    linearstar.setVisibility(View.VISIBLE);
                }
            }
        });


        bstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = staredit.getText().toString();
                if (star.matches("")) {
                    toastMessage("Please Enter Zodiac Sign");
                } else {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("star", star);
                    editor.apply();
                    linearstar.setVisibility(View.GONE);

                    linearpop.setVisibility(View.VISIBLE);
                }
            }
        });
        bbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup();
            }
        });
        btnfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent broswerpdf = new Intent(Intent.ACTION_GET_CONTENT);
                broswerpdf.setType("application/pdf");
                broswerpdf.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(broswerpdf, "Select PDF"), PICK_PDF_CODE);
            }
        });


        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle("Excel Upload");
        mProgressDialog.setMessage("Please wait, excel is uploading...");
        noteEt = findViewById(R.id.note);
        nameEt = findViewById(R.id.name);
        upload = findViewById(R.id.upload);
        upiIdEt = findViewById(R.id.upi_id);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the values from the EditTexts
                String amount = amountEt.getText().toString();
                String note = "Dakshna";
                String name = "Dakshna";
                String upiId = "coolkrishna7235@oksbi";
                payUsingUpi(amount, upiId, name, note);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                pathHistory = new ArrayList<String>();
                upload.setVisibility(View.GONE);
                pathHistory.add(count, System.getenv("EXTERNAL_STORAGE"));
                Log.d(TAG, "btnSDCard: " + pathHistory.get(count));
                checkInternalStorage();
            }
        });
//       mSignaturePad = findViewById(R.id.signature_pad);
//       mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
//
//           @Override
//           public void onStartSigning() {
//               //Event triggered when the pad is touched
//          }
//
//          @Override
//            public void onSigned() {
//             //Event triggered when the pad is signed
//                bclear.setEnabled(true);
//               bsave.setEnabled(true);
//          }
//
//           @Override
//           public void onClear() {
//               //Event triggered when the pad is cleared
//
//               bclear.setEnabled(false);
//               bsave.setEnabled(false);
//           }
//       });
        lvInternalStorage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lastDirectory = pathHistory.get(count);
                if (lastDirectory.equals(adapterView.getItemAtPosition(i))) {
                    Log.d(TAG, "lvInternalStorage: Selected a file for upload: " + lastDirectory);

                    //Execute method for reading the excel data.
                    readExcelData(lastDirectory);

                } else {
                    count++;
                    pathHistory.add(count, (String) adapterView.getItemAtPosition(i));
                    checkInternalStorage();
                    Log.d(TAG, "lvInternalStorage: " + pathHistory.get(count));
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkFilePermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int permissionCheck = this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void readExcelData(String filePath) {
        //mProgressDialog.show();
        Log.d(TAG, "readExcelData: Reading Excel File.");
        // toastMessage("excelstarted");
        //decarle input file


        Toast.makeText(MainActivity.this, filePath, Toast.LENGTH_SHORT).show();
        linearupload.setVisibility(View.GONE);


        File imgFile = new File(filePath);
        pdfView.fromFile(imgFile);
//        if(imgFile.exists()){
//
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//
//
//            imageView.setImageBitmap(myBitmap);
//
//        }

        linearimage.setVisibility(View.VISIBLE);


        // File inputFile = new File(filePath);
        //inputFile.renameTo(new File("/sdcard/Pictures/newName.file"));

    }

    private void stop() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
    public void start(){
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.audio);
            player.start();
            player.setLooping(true);
        }

    }
    private void checkInternalStorage() {
        Log.d(TAG, "checkInternalStorage: Started.");
        try {
            if (!Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                toastMessage("No SD card found.");
            } else {
                // Locate the image folder in your SD Car;d
                file = new File(pathHistory.get(count));
                Log.d(TAG, "checkInternalStorage: directory path: " + pathHistory.get(count));
            }

            listFile = file.listFiles();

            // Create a String array for FilePathStrings
            FilePathStrings = new String[listFile.length];

            // Create a String array for FileNameStrings
            FileNameStrings = new String[listFile.length];

            for (int i = 0; i < listFile.length; i++) {
                // Get the path of the image file
                FilePathStrings[i] = listFile[i].getAbsolutePath();
                // Get the name image file
                FileNameStrings[i] = listFile[i].getName();
            }

            for (int i = 0; i < listFile.length; i++) {
                Log.d("Files", "FileName:" + listFile[i].getName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FilePathStrings);
            lvInternalStorage.setAdapter(adapter);

        } catch (NullPointerException e) {
            Log.e(TAG, "checkInternalStorage: NULLPOINTEREXCEPTION " + e.getMessage());
        }
    }

    void payUsingUpi(String amount, String upiId, String name, String note) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();


        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        // check if intent resolves
        if (null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(MainActivity.this, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show();
        }

    }

    void initializeViews() {
        send = findViewById(R.id.send);
        amountEt = findViewById(R.id.amount_et);
        noteEt = findViewById(R.id.note);
        nameEt = findViewById(R.id.name);
        upiIdEt = findViewById(R.id.upi_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null) {
            { pdf=true;image=false;
               //linearupload.setVisibility(View.GONE);
                selectpdf=data.getData();
                parentrelative.setVisibility(View.GONE);
                karpooramgif.setVisibility(View.VISIBLE);
                startbookpoojaMandram();

                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojakarakal);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        parentrelative.setVisibility(View.VISIBLE);
                        karpooramgif.setVisibility(View.GONE);

                        lasttool="book";

                        stop();
                        //start();
                        ShowPaymentPopup();
                    }
                }, 19000);



//                linearpdf.setVisibility(View.VISIBLE);

//                Uri pdffile=Uri.parse(selectpdf.toString());
//
//
//                pdfView.fromUri(pdffile).password(null).defaultPage(0).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true)
//                            .onDraw(new OnDrawListener() {
//                                @Override
//                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//
//                                }
//                            }).onDrawAll(new OnDrawListener() {
//                    @Override
//                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//
//                    }
//                }).onPageError(new OnPageErrorListener() {
//                    @Override
//                    public void onPageError(int page, Throwable t) {
//                        Toast.makeText(MainActivity.this,"Eror",Toast.LENGTH_SHORT).show();
//                    }
//                }).onPageChange(new OnPageChangeListener() {
//                    @Override
//                    public void onPageChanged(int page, int pageCount) {
//
//                    }
//                }).onTap(new OnTapListener() {
//                    @Override
//                    public boolean onTap(MotionEvent e) {
//                        return true;
//                    }
//                }).onRender(new OnRenderListener() {
//                    @Override
//                    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
//                        pdfView.fitToWidth();
//                    }
//                }).enableAnnotationRendering(true).invalidPageColor(Color.WHITE).load()
//                ;


                 // ShowPaymentPopup();
            }
        }

        if (requestCode == PICK_IMAGE_CODE && resultCode == RESULT_OK && data != null) {
            {
                pdf=false;image=true;
//            {    linearupload.setVisibility(View.GONE);
//                linearimage.setVisibility(View.VISIBLE);
 selectpdf=data.getData();
              imageUri=Uri.parse(selectpdf.toString());
//
//                imageView.setImageURI(pdffile);
                parentrelative.setVisibility(View.GONE);
                karpooramgif.setVisibility(View.VISIBLE);
                startbookpoojaMandram();

                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojakarakal);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        parentrelative.setVisibility(View.VISIBLE);
                        karpooramgif.setVisibility(View.GONE);
                        bbook.setVisibility(View.GONE);
                        lasttool="book";
                        readbook.setVisibility(View.VISIBLE);
                        stop();
                        //start();
                        ShowPaymentPopup();
                    }
                }, 19000);


            }


        }


        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(MainActivity.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: " + str);
            String paymentCancel = "";
            if (str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if (equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    } else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                } else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here
                //
                //
                if(pdf==true){

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("pdf", String.valueOf(selectpdf));
                    editor.apply();
                    bbook.setVisibility(View.GONE);
                    readbook.setVisibility(View.VISIBLE);
                }
                if(image==true){

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("image", selectpdf.getPath());
                    editor.apply();
                    bbook.setVisibility(View.GONE);
                    readbook.setVisibility(View.VISIBLE);
                }
                if(lasttool=="pen"){
                    showPenpoojaend();
                }if(lasttool=="book") {
                    showbookpoojaend();

                }
                if(lasttool=="lap"){
                    showLappoojaend();
                }
                if(lasttool=="car"){
                    showCarpoojaend();
                }
                if(lasttool=="guitar"){
                    showguitarpoojaend();
                }
                if(lasttool=="camera"){
                    showCamerapoojaend();
                }
                if(lasttool=="dumbel"){
                    showDumbelpoojaend();
                }
                Toast.makeText(MainActivity.this, "Dakshna samarpanam successful.", Toast.LENGTH_SHORT).show();
                Log.d("UPI", "responseStr: " + approvalRefNo);
            } else if ("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(MainActivity.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }








    public void ShowPopup() {

        Button bimage, bpdf;
        myDialog.setContentView(R.layout.bookpooja);
//        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
//        txtclose.setText("M");
        bimage = (Button) myDialog.findViewById(R.id.bimage);
        bpdf = (Button) myDialog.findViewById(R.id.bpdf);
        bpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Intent broswerpdf = new Intent(Intent.ACTION_GET_CONTENT);
                broswerpdf.setType("application/pdf");
                broswerpdf.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(broswerpdf, "Select PDF"), PICK_PDF_CODE);

            }
        });
        bimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Intent broswerpdf = new Intent(Intent.ACTION_GET_CONTENT);
                broswerpdf.setType("image/*");
                broswerpdf.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(broswerpdf, "Select IMAGE"), PICK_IMAGE_CODE);

            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void ShowToolPopup() {

        Button bpen, blap,bcar,bguitar,bcamera,bdumbel;
        myDialog.setContentView(R.layout.toolsicon);
//        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
//        txtclose.setText("M");
       // bimage = (Button) myDialog.findViewById(R.id.bimage);
        bpen = (Button) myDialog.findViewById(R.id.bpen);
        blap = (Button) myDialog.findViewById(R.id.blap);
        bguitar= (Button) myDialog.findViewById(R.id.bguitar);
        bcar=(Button) myDialog.findViewById(R.id.bcar);
        bcamera=(Button) myDialog.findViewById(R.id.bcamera);
        bdumbel=(Button) myDialog.findViewById(R.id.bdumbel);
        bpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

                parentrelative.setVisibility(View.GONE);
                karpooramgif.setVisibility(View.VISIBLE);


                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojapengi);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                       karpooramgif.setVisibility(View.GONE);
                       lasttool="pen";
                        ShowPaymentPopup();
                        parentrelative.setVisibility(View.VISIBLE);
                        //start();
                        //parentrelative.setBackground(R.drawable.poojabooks);
                        //parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.poojabooks) );
                    }
                }, 4000);
                ;

            }
        });

        bguitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

                parentrelative.setVisibility(View.GONE);
                karpooramgif.setVisibility(View.VISIBLE);


                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojaguitargi);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        karpooramgif.setVisibility(View.GONE);
                        lasttool="guitar";
                        ShowPaymentPopup();
                        parentrelative.setVisibility(View.VISIBLE);
                        //start();
                        //parentrelative.setBackground(R.drawable.poojabooks);
                        //parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.poojabooks) );
                    }
                }, 1100);
                ;

            }
        });
        bdumbel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

                parentrelative.setVisibility(View.GONE);
                karpooramgif.setVisibility(View.VISIBLE);


                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojadumbelflower);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        karpooramgif.setVisibility(View.GONE);
                        lasttool="dumbel";
                        ShowPaymentPopup();
                        parentrelative.setVisibility(View.VISIBLE);
                        //start();
                        //parentrelative.setBackground(R.drawable.poojabooks);
                        //parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.poojabooks) );
                    }
                }, 1600);
                ;

            }
        });
        bcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

                parentrelative.setVisibility(View.GONE);
                karpooramgif.setVisibility(View.VISIBLE);


                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojacamgi);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        karpooramgif.setVisibility(View.GONE);
                        lasttool="camera";
                        ShowPaymentPopup();
                        parentrelative.setVisibility(View.VISIBLE);
                        //start();
                        //parentrelative.setBackground(R.drawable.poojabooks);
                        //parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.poojabooks) );
                    }
                }, 1500);
                ;

            }
        });
        blap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

                parentrelative.setVisibility(View.GONE);
                karpooramgif.setVisibility(View.VISIBLE);


                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojalap);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        karpooramgif.setVisibility(View.GONE);
                        lasttool="lap";
                        ShowPaymentPopup();
                        parentrelative.setVisibility(View.VISIBLE);
                        //start();
                        //parentrelative.setBackground(R.drawable.poojabooks);
                        //parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.poojabooks) );
                    }
                }, 1700);
                ;

            }
        });

        bcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                lasttool="car";
                ShowPaymentPopup();



                ;

            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void ShowPaymentPopup() {

        Button bimage, bpdf, send1;
        TextView plus, minus;
        final EditText amountEt1;
        myDialogPayment.setContentView(R.layout.payment);
        amountEt1 = myDialogPayment.findViewById(R.id.amount_et);

        send1 = (Button) myDialogPayment.findViewById(R.id.sendnew);
        plus = myDialogPayment.findViewById(R.id.plus);
        minus = myDialogPayment.findViewById(R.id.minus);
//        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
//        txtclose.setText("M");
        send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // toastMessage("new");
                //Getting the values from the EditTexts
                String amount = amountEt1.getText().toString();
                String note = "Dakshna";
                String name = "Dakshna";
                String upiId = "amaljoy1432@okicici";
             // payUsingUpi(amount, upiId, name, note);
                if(pdf==true){

//                    SharedPreferences.Editor editor = sharedPref.edit();
//                    editor.putString("pdf", String.valueOf(selectpdf));
//                    editor.apply();
                    bbook.setVisibility(View.GONE);
                    readbook.setVisibility(View.VISIBLE);
                }
                if(image==true){

//                    SharedPreferences.Editor editor = sharedPref.edit();
//                    editor.putString("image", getRealPathFromURI(getApplicationContext(),selectpdf));
//                    editor.apply();
//                    imagepath=getRealPathFromURI(getApplicationContext(),selectpdf);
                    bbook.setVisibility(View.GONE);
                    readbook.setVisibility(View.VISIBLE);
                }

                myDialogPayment.dismiss();
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the values from the EditTexts
                int amount = Integer.parseInt(amountEt1.getText().toString());
                amount = amount + 1;
                amountEt1.setText(amount + "");

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(amountEt1.getText().toString());
                amount = amount - 1;
                amountEt1.setText(amount + "");
            }
        });

        myDialogPayment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogPayment.show();
    }

    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            Log.e(TAG, "getRealPathFromURI Exception : " + e.toString());
            return "";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public void startsank() {


     stop();

        if (player == null) {
            player = MediaPlayer.create(this, R.raw.sank);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stop();
                    start();
                }
            });


        }
    }
    public void startbell() {


        stop();

        if (player == null) {
            player = MediaPlayer.create(this, R.raw.bella);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stop();
                    start();
                }
            });


        }
    }


    public void startbookpoojaMandram() {


        stop();

        if (player == null) {
            player = MediaPlayer.create(this, R.raw.poojaaudio);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stop();
                    start();
                }
            });


        }
    }


    public void showbookpoojaend(){
        parentrelative.setVisibility(View.GONE);
        karpooramgif.setVisibility(View.VISIBLE);


        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojaend);
            karpooram.setImageDrawable(gifDrawable);
        } catch (Resources.NotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }





        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                parentrelative.setVisibility(View.VISIBLE);
                karpooramgif.setVisibility(View.GONE);
                start();
                //parentrelative.setBackground(R.drawable.poojabooks);
                parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.poojabooks) );
            }
        }, 4000);
    }
    public void showCarpoojaend(){
        parentrelative.setVisibility(View.GONE);
        karpooramgif.setVisibility(View.VISIBLE);


        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojakeygi);
            karpooram.setImageDrawable(gifDrawable);
        } catch (Resources.NotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }





        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                parentrelative.setVisibility(View.VISIBLE);
                karpooramgif.setVisibility(View.GONE);
                start();
                //parentrelative.setBackground(R.drawable.poojabooks);
                parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.poojakeybooks) );
            }
        }, 1900);
    }
    public void showLappoojaend(){

                parentrelative.setVisibility(View.VISIBLE);
                karpooramgif.setVisibility(View.GONE);
                start();
                //parentrelative.setBackground(R.drawable.poojabooks);
                parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.lapbook) );

    }
    public void showguitarpoojaend(){

        parentrelative.setVisibility(View.VISIBLE);
        karpooramgif.setVisibility(View.GONE);
        start();
        //parentrelative.setBackground(R.drawable.poojabooks);
        parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.guitarwithbooknew) );

    }
    public void showCamerapoojaend(){

        parentrelative.setVisibility(View.VISIBLE);
        karpooramgif.setVisibility(View.GONE);
        start();
        //parentrelative.setBackground(R.drawable.poojabooks);
        parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.poojacambook) );

    }
    public void showDumbelpoojaend(){

        parentrelative.setVisibility(View.VISIBLE);
        karpooramgif.setVisibility(View.GONE);
        start();
        //parentrelative.setBackground(R.drawable.poojabooks);
        parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bookanddumbel) );

    }

    public void checkSharedPrefrence(){
        String value = sharedPref.getString("language",null);
        if (value == null) {
            toastMessage("no");
        } else {


            linearlanguage.setVisibility(View.GONE);



            chandathirigif.setVisibility(View.VISIBLE);

            try {
                GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.newdor);
                chandathiri.setImageDrawable(gifDrawable);
            } catch (Resources.NotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }





            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    parentrelative.setVisibility(View.VISIBLE);
                    chandathirigif.setVisibility(View.GONE);
                    linearname.setVisibility(View.VISIBLE);
                    String value1 = sharedPref.getString("name",null);
                    if(value1!=null){
                        linearname.setVisibility(View.GONE);
                        linearstar.setVisibility(View.VISIBLE);
                    }
                    String value2 = sharedPref.getString("name",null);
                    if(value2!=null){
                    linearstar.setVisibility(View.GONE);

                    linearpop.setVisibility(View.VISIBLE);}

                    String value3 = sharedPref.getString("image",null);
                    toastMessage(value3);
                    if(value3!=null){
                        //selectpdf=Uri.parse(new File(value3).toString());
                        imagepath=value3;
                        image=true;
                        pdf=false;
                        bbook.setVisibility(View.GONE);
                        readbook.setVisibility(View.VISIBLE);
                     }

                String value4 = sharedPref.getString("pdf",null);
            if(value4!=null){
                selectpdf1=value3;
                image=false;
                pdf=true;
                bbook.setVisibility(View.GONE);
                readbook.setVisibility(View.VISIBLE);
                    }

                }

            }, 1500);

        }







}
    public void showPenpoojaend(){
        parentrelative.setVisibility(View.GONE);
        karpooramgif.setVisibility(View.VISIBLE);


        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojapenend);
            karpooram.setImageDrawable(gifDrawable);
        } catch (Resources.NotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }





        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                parentrelative.setVisibility(View.VISIBLE);
                karpooramgif.setVisibility(View.GONE);
                start();
                //parentrelative.setBackground(R.drawable.poojabooks);
                parentrelative.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.poojapennobook) );
            }
        }, 2000);
    }
    @Override
    protected void onSaveInstanceState(Bundle oldInstanceState)
    {
        super.onSaveInstanceState(oldInstanceState);
        oldInstanceState.clear();
    }

}