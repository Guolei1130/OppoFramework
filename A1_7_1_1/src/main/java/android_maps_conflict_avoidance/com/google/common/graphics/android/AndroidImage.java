package android_maps_conflict_avoidance.com.google.common.graphics.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android_maps_conflict_avoidance.com.google.common.graphics.GoogleGraphics;
import android_maps_conflict_avoidance.com.google.common.graphics.GoogleImage;
import java.util.Map;

/*  JADX ERROR: NullPointerException in pass: ReSugarCode
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
    	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ExtractFieldInit.checkStaticFieldsInit(ExtractFieldInit.java:58)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public class AndroidImage implements GoogleImage {
    private static volatile int bitmapCount;
    private volatile Bitmap bitmap;
    private final boolean isOriginal;
    private boolean pinned;

    /*  JADX ERROR: NullPointerException in pass: EnumVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.util.ArrayList.forEach(ArrayList.java:1251)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    public enum AutoScale {
        ;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android_maps_conflict_avoidance.com.google.common.graphics.android.AndroidImage.AutoScale.<clinit>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 6 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android_maps_conflict_avoidance.com.google.common.graphics.android.AndroidImage.AutoScale.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: android_maps_conflict_avoidance.com.google.common.graphics.android.AndroidImage.AutoScale.<clinit>():void");
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android_maps_conflict_avoidance.com.google.common.graphics.android.AndroidImage.<clinit>():void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 5 more
        */
    static {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android_maps_conflict_avoidance.com.google.common.graphics.android.AndroidImage.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: android_maps_conflict_avoidance.com.google.common.graphics.android.AndroidImage.<clinit>():void");
    }

    public AndroidImage(byte[] imageData, int imageOffset, int imageLength) {
        this.pinned = false;
        this.bitmap = BitmapFactory.decodeByteArray(imageData, imageOffset, imageLength);
        if (this.bitmap == null) {
            throw new IllegalStateException("Null Bitmap!");
        }
        synchronized (AndroidImage.class) {
            bitmapCount++;
            this.isOriginal = true;
        }
    }

    public AndroidImage(int width, int height) {
        this(width, height, true);
    }

    public AndroidImage(int width, int height, boolean processAlpha) {
        this.pinned = false;
        this.bitmap = Bitmap.createBitmap(width, height, processAlpha ? Config.ARGB_8888 : Config.RGB_565);
        if (this.bitmap == null) {
            throw new IllegalStateException("Null Bitmap!");
        }
        synchronized (AndroidImage.class) {
            bitmapCount++;
            this.isOriginal = true;
        }
    }

    public AndroidImage(Bitmap bitmap) {
        this.pinned = false;
        this.bitmap = bitmap;
        this.isOriginal = false;
    }

    public AndroidImage(Context context, Map<String, Integer> stringIdMap, String name, AutoScale autoScale) {
        this.pinned = false;
        String cleanName = cleanName(name);
        if (stringIdMap != null) {
            Integer resourceId = (Integer) stringIdMap.get(cleanName);
            if (resourceId != null) {
                Options options = null;
                if (autoScale == AutoScale.AUTO_SCALE_DISABLED) {
                    options = new Options();
                    options.inScaled = false;
                }
                this.bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId.intValue(), options);
                if (autoScale == AutoScale.AUTO_SCALE_DISABLED && this.bitmap != null) {
                    this.bitmap.setDensity(android_maps_conflict_avoidance.com.google.common.Config.getInstance().getPixelsPerInch());
                }
            }
        }
        if (this.bitmap == null) {
            this.bitmap = BitmapFactory.decodeFile(name);
        }
        if (this.bitmap == null) {
            throw new IllegalStateException("Null Bitmap! \"" + name + "\"; if seen during a test, " + "this usually means that the image file needs to be added to the test.config file");
        }
        synchronized (AndroidImage.class) {
            bitmapCount++;
            this.isOriginal = true;
        }
    }

    private static String cleanName(String name) {
        if (name.indexOf("/") == 0) {
            name = name.substring(1);
        }
        int dotIndex = name.indexOf(".");
        if (dotIndex > 0) {
            return name.substring(0, dotIndex);
        }
        return name;
    }

    public void pin() {
        this.pinned = true;
    }

    public void recycle() {
        if (!this.pinned && this.bitmap != null) {
            this.bitmap.recycle();
            this.bitmap = null;
        }
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public int getWidth() {
        return this.bitmap.getWidth();
    }

    public int getHeight() {
        return this.bitmap.getHeight();
    }

    public GoogleGraphics getGraphics() {
        return new AndroidGraphics(new Canvas(this.bitmap));
    }

    public GoogleImage createScaledImage(int srcX, int srcY, int srcWidth, int srcHeight, int newWidth, int newHeight) {
        ScaledAndroidImage image = new ScaledAndroidImage(this, newWidth, newHeight, srcX, srcY, srcWidth, srcHeight);
        if (newWidth * newHeight < 4096) {
            image.getGraphics();
        }
        return image;
    }

    public void drawImage(GoogleGraphics g, int x, int y) {
        ((AndroidGraphics) g).getCanvas().drawBitmap(this.bitmap, (float) x, (float) y, null);
    }

    protected void finalize() throws Throwable {
        compact();
        super.finalize();
    }

    private void compact() {
        if (this.isOriginal) {
            Bitmap b;
            synchronized (this) {
                b = this.bitmap;
                this.bitmap = null;
            }
            if (b != null) {
                synchronized (AndroidImage.class) {
                    bitmapCount--;
                    if (bitmapCount < 0) {
                        throw new IllegalStateException();
                    }
                }
                return;
            }
            return;
        }
        this.bitmap = null;
    }
}