package android.content.pm;

import android.accounts.GrantCredentialsPermissionActivity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.AtomicFile;
import android.util.AttributeSet;
import android.util.IntArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.FastXmlSerializer;
import com.google.android.collect.Lists;
import com.google.android.collect.Maps;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.utils.BlockUtils.isAllBlocksEmpty(BlockUtils.java:546)
    	at jadx.core.dex.visitors.ExtractFieldInit.getConstructorsList(ExtractFieldInit.java:221)
    	at jadx.core.dex.visitors.ExtractFieldInit.moveCommonFieldsInit(ExtractFieldInit.java:121)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:46)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:42)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ClassModifier.removeFieldUsageFromConstructor(ClassModifier.java:100)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticFields(ClassModifier.java:75)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:48)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:40)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public abstract class RegisteredServicesCache<V> {
    private static final boolean DEBUG = false;
    protected static final String REGISTERED_SERVICES_DIR = "registered_services";
    private static final String TAG = "PackageManager";
    private final String mAttributesName;
    public final Context mContext;
    private final BroadcastReceiver mExternalReceiver;
    private Handler mHandler;
    private final String mInterfaceName;
    private RegisteredServicesCacheListener<V> mListener;
    private final String mMetaDataName;
    private final BroadcastReceiver mPackageReceiver;
    private final XmlSerializerAndParser<V> mSerializerAndParser;
    protected final Object mServicesLock;
    private final BroadcastReceiver mUserRemovedReceiver;
    @GuardedBy("mServicesLock")
    private final SparseArray<UserServices<V>> mUserServices;

    /* renamed from: android.content.pm.RegisteredServicesCache$4 */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ RegisteredServicesCache this$0;
        final /* synthetic */ RegisteredServicesCacheListener val$listener2;
        final /* synthetic */ boolean val$removed;
        final /* synthetic */ Object val$type;
        final /* synthetic */ int val$userId;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e8 in method: android.content.pm.RegisteredServicesCache.4.<init>(android.content.pm.RegisteredServicesCache, android.content.pm.RegisteredServicesCacheListener, java.lang.Object, int, boolean):void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e8
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 6 more
            */
        AnonymousClass4(android.content.pm.RegisteredServicesCache r1, android.content.pm.RegisteredServicesCacheListener r2, java.lang.Object r3, int r4, boolean r5) {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e8 in method: android.content.pm.RegisteredServicesCache.4.<init>(android.content.pm.RegisteredServicesCache, android.content.pm.RegisteredServicesCacheListener, java.lang.Object, int, boolean):void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: android.content.pm.RegisteredServicesCache.4.<init>(android.content.pm.RegisteredServicesCache, android.content.pm.RegisteredServicesCacheListener, java.lang.Object, int, boolean):void");
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: android.content.pm.RegisteredServicesCache.4.run():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e5
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 6 more
            */
        public void run() {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: android.content.pm.RegisteredServicesCache.4.run():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: android.content.pm.RegisteredServicesCache.4.run():void");
        }
    }

    public static class ServiceInfo<V> {
        public final ComponentInfo componentInfo;
        public final ComponentName componentName;
        public final V type;
        public final int uid;

        public ServiceInfo(V type, ComponentInfo componentInfo, ComponentName componentName) {
            this.type = type;
            this.componentInfo = componentInfo;
            this.componentName = componentName;
            this.uid = componentInfo != null ? componentInfo.applicationInfo.uid : -1;
        }

        public String toString() {
            return "ServiceInfo: " + this.type + ", " + this.componentName + ", uid " + this.uid;
        }
    }

    private static class UserServices<V> {
        @GuardedBy("mServicesLock")
        boolean mPersistentServicesFileDidNotExist;
        @GuardedBy("mServicesLock")
        final Map<V, Integer> persistentServices;
        @GuardedBy("mServicesLock")
        Map<V, ServiceInfo<V>> services;

        /* synthetic */ UserServices(UserServices userServices) {
            this();
        }

        private UserServices() {
            this.persistentServices = Maps.newHashMap();
            this.services = null;
            this.mPersistentServicesFileDidNotExist = true;
        }
    }

    public abstract V parseServiceAttributes(Resources resources, String str, AttributeSet attributeSet);

    @GuardedBy("mServicesLock")
    private UserServices<V> findOrCreateUserLocked(int userId) {
        return findOrCreateUserLocked(userId, true);
    }

    @GuardedBy("mServicesLock")
    private UserServices<V> findOrCreateUserLocked(int userId, boolean loadFromFileIfNew) {
        UserServices<V> services = (UserServices) this.mUserServices.get(userId);
        if (services == null) {
            services = new UserServices();
            this.mUserServices.put(userId, services);
            if (loadFromFileIfNew && this.mSerializerAndParser != null) {
                UserInfo user = getUser(userId);
                if (user != null) {
                    AtomicFile file = createFileForUser(user.id);
                    if (file.getBaseFile().exists()) {
                        AutoCloseable autoCloseable = null;
                        try {
                            autoCloseable = file.openRead();
                            readPersistentServicesLocked(autoCloseable);
                        } catch (Exception e) {
                            Log.w(TAG, "Error reading persistent services for user " + user.id, e);
                        } finally {
                            IoUtils.closeQuietly(autoCloseable);
                        }
                    }
                }
            }
        }
        return services;
    }

    public RegisteredServicesCache(Context context, String interfaceName, String metaDataName, String attributeName, XmlSerializerAndParser<V> serializerAndParser) {
        this.mServicesLock = new Object();
        this.mUserServices = new SparseArray(2);
        this.mPackageReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int uid = intent.getIntExtra(Intent.EXTRA_UID, -1);
                if (uid != -1) {
                    RegisteredServicesCache.this.handlePackageEvent(intent, UserHandle.getUserId(uid));
                }
            }
        };
        this.mExternalReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                RegisteredServicesCache.this.handlePackageEvent(intent, 0);
            }
        };
        this.mUserRemovedReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                RegisteredServicesCache.this.onUserRemoved(intent.getIntExtra(Intent.EXTRA_USER_HANDLE, -1));
            }
        };
        this.mContext = context;
        this.mInterfaceName = interfaceName;
        this.mMetaDataName = metaDataName;
        this.mAttributesName = attributeName;
        this.mSerializerAndParser = serializerAndParser;
        migrateIfNecessaryLocked();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        this.mContext.registerReceiverAsUser(this.mPackageReceiver, UserHandle.ALL, intentFilter, null, null);
        IntentFilter sdFilter = new IntentFilter();
        sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
        sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
        this.mContext.registerReceiver(this.mExternalReceiver, sdFilter);
        IntentFilter userFilter = new IntentFilter();
        sdFilter.addAction(Intent.ACTION_USER_REMOVED);
        this.mContext.registerReceiver(this.mUserRemovedReceiver, userFilter);
    }

    private final void handlePackageEvent(Intent intent, int userId) {
        boolean isRemoval;
        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            isRemoval = true;
        } else {
            isRemoval = Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE.equals(action);
        }
        boolean replacing = intent.getBooleanExtra(Intent.EXTRA_REPLACING, false);
        if (!isRemoval || !replacing) {
            int[] uids = null;
            if (Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE.equals(action) || Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE.equals(action)) {
                uids = intent.getIntArrayExtra(Intent.EXTRA_CHANGED_UID_LIST);
            } else {
                if (intent.getIntExtra(Intent.EXTRA_UID, -1) > 0) {
                    uids = new int[]{intent.getIntExtra(Intent.EXTRA_UID, -1)};
                }
            }
            generateServicesMap(uids, userId);
        }
    }

    public void invalidateCache(int userId) {
        synchronized (this.mServicesLock) {
            findOrCreateUserLocked(userId).services = null;
            onServicesChangedLocked(userId);
        }
    }

    public void dump(FileDescriptor fd, PrintWriter fout, String[] args, int userId) {
        synchronized (this.mServicesLock) {
            UserServices<V> user = findOrCreateUserLocked(userId);
            if (user.services != null) {
                fout.println("RegisteredServicesCache: " + user.services.size() + " services");
                for (ServiceInfo<?> info : user.services.values()) {
                    fout.println("  " + info);
                }
            } else {
                fout.println("RegisteredServicesCache: services not loaded");
            }
        }
    }

    public RegisteredServicesCacheListener<V> getListener() {
        RegisteredServicesCacheListener<V> registeredServicesCacheListener;
        synchronized (this) {
            registeredServicesCacheListener = this.mListener;
        }
        return registeredServicesCacheListener;
    }

    public void setListener(RegisteredServicesCacheListener<V> listener, Handler handler) {
        if (handler == null) {
            handler = new Handler(this.mContext.getMainLooper());
        }
        synchronized (this) {
            this.mHandler = handler;
            this.mListener = listener;
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ModVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.ModVisitor.getParentInsnSkipMove(ModVisitor.java:320)
        	at jadx.core.dex.visitors.ModVisitor.getArgsToFieldsMapping(ModVisitor.java:294)
        	at jadx.core.dex.visitors.ModVisitor.processAnonymousConstructor(ModVisitor.java:253)
        	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:235)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(ArrayList.java:1251)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    private void notifyListener(V r9, int r10, boolean r11) {
        /*
        r8 = this;
        monitor-enter(r8);
        r2 = r8.mListener;	 Catch:{ all -> 0x0009 }
        r6 = r8.mHandler;	 Catch:{ all -> 0x0009 }
        monitor-exit(r8);
        if (r2 != 0) goto L_0x000c;
    L_0x0008:
        return;
    L_0x0009:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x000c:
        r7 = r2;
        r0 = new android.content.pm.RegisteredServicesCache$4;
        r1 = r8;
        r3 = r9;
        r4 = r10;
        r5 = r11;
        r0.<init>(r1, r2, r3, r4, r5);
        r6.post(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.RegisteredServicesCache.notifyListener(java.lang.Object, int, boolean):void");
    }

    public ServiceInfo<V> getServiceInfo(V type, int userId) {
        ServiceInfo<V> serviceInfo;
        synchronized (this.mServicesLock) {
            UserServices<V> user = findOrCreateUserLocked(userId);
            if (user.services == null) {
                generateServicesMap(null, userId);
            }
            serviceInfo = (ServiceInfo) user.services.get(type);
        }
        return serviceInfo;
    }

    public Collection<ServiceInfo<V>> getAllServices(int userId) {
        Collection<ServiceInfo<V>> unmodifiableCollection;
        synchronized (this.mServicesLock) {
            UserServices<V> user = findOrCreateUserLocked(userId);
            if (user.services == null) {
                generateServicesMap(null, userId);
            }
            unmodifiableCollection = Collections.unmodifiableCollection(new ArrayList(user.services.values()));
        }
        return unmodifiableCollection;
    }

    /* JADX WARNING: Missing block: B:10:0x0019, code:
            r6 = null;
            r5 = r0.iterator();
     */
    /* JADX WARNING: Missing block: B:12:0x0022, code:
            if (r5.hasNext() == false) goto L_0x0056;
     */
    /* JADX WARNING: Missing block: B:13:0x0024, code:
            r4 = (android.content.pm.RegisteredServicesCache.ServiceInfo) r5.next();
            r9 = r4.componentInfo.applicationInfo.versionCode;
            r2 = null;
     */
    /* JADX WARNING: Missing block: B:15:?, code:
            r2 = r12.mContext.getPackageManager().getApplicationInfoAsUser(r4.componentInfo.packageName, 0, r13);
     */
    /* JADX WARNING: Missing block: B:25:0x0056, code:
            if (r6 == null) goto L_0x0065;
     */
    /* JADX WARNING: Missing block: B:27:0x005c, code:
            if (r6.size() <= 0) goto L_0x0065;
     */
    /* JADX WARNING: Missing block: B:28:0x005e, code:
            generateServicesMap(r6.toArray(), r13);
     */
    /* JADX WARNING: Missing block: B:29:0x0065, code:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateServices(int userId) {
        synchronized (this.mServicesLock) {
            UserServices<V> user = findOrCreateUserLocked(userId);
            if (user.services == null) {
                return;
            }
            List<ServiceInfo<V>> allServices = new ArrayList(user.services.values());
        }
        if (newAppInfo == null || newAppInfo.versionCode != versionCode) {
            IntArray updatedUids;
            if (updatedUids == null) {
                updatedUids = new IntArray();
            }
            updatedUids.add(service.uid);
        }
    }

    protected boolean inSystemImage(int callerUid) {
        String[] packages = this.mContext.getPackageManager().getPackagesForUid(callerUid);
        if (packages == null) {
            return false;
        }
        int length = packages.length;
        int i = 0;
        while (i < length) {
            try {
                if ((this.mContext.getPackageManager().getPackageInfo(packages[i], 0).applicationInfo.flags & 1) != 0) {
                    return true;
                }
                i++;
            } catch (NameNotFoundException e) {
                return false;
            }
        }
        return false;
    }

    protected List<ResolveInfo> queryIntentServices(int userId) {
        return this.mContext.getPackageManager().queryIntentServicesAsUser(new Intent(this.mInterfaceName), 786560, userId);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0044 A:{ExcHandler: org.xmlpull.v1.XmlPullParserException (r6_0 'e' java.lang.Exception), Splitter: B:4:0x001d} */
    /* JADX WARNING: Missing block: B:9:0x0044, code:
            r6 = move-exception;
     */
    /* JADX WARNING: Missing block: B:10:0x0045, code:
            android.util.Log.w(TAG, "Unable to load service info " + r11.toString(), r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generateServicesMap(int[] changedUids, int userId) {
        ServiceInfo<V> info;
        ArrayList<ServiceInfo<V>> serviceInfos = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentServices(userId)) {
            try {
                info = parseServiceInfo(resolveInfo);
                if (info == null) {
                    Log.w(TAG, "Unable to load service info " + resolveInfo.toString());
                } else {
                    serviceInfos.add(info);
                }
            } catch (Exception e) {
            }
        }
        synchronized (this.mServicesLock) {
            UserServices<V> user = findOrCreateUserLocked(userId);
            boolean firstScan = user.services == null;
            if (firstScan) {
                user.services = Maps.newHashMap();
            }
            StringBuilder changes = new StringBuilder();
            boolean changed = false;
            for (ServiceInfo<V> info2 : serviceInfos) {
                Integer previousUid = (Integer) user.persistentServices.get(info2.type);
                if (previousUid == null) {
                    changed = true;
                    user.services.put(info2.type, info2);
                    user.persistentServices.put(info2.type, Integer.valueOf(info2.uid));
                    if (!(user.mPersistentServicesFileDidNotExist ? firstScan : false)) {
                        notifyListener(info2.type, userId, false);
                    }
                } else {
                    if (previousUid.intValue() == info2.uid) {
                        user.services.put(info2.type, info2);
                    } else {
                        if (!inSystemImage(info2.uid)) {
                            if (containsTypeAndUid(serviceInfos, info2.type, previousUid.intValue())) {
                            }
                        }
                        changed = true;
                        user.services.put(info2.type, info2);
                        user.persistentServices.put(info2.type, Integer.valueOf(info2.uid));
                        notifyListener(info2.type, userId, false);
                    }
                }
            }
            ArrayList<V> toBeRemoved = Lists.newArrayList();
            for (V v1 : user.persistentServices.keySet()) {
                if (!containsType(serviceInfos, v1) && containsUid(changedUids, ((Integer) user.persistentServices.get(v1)).intValue())) {
                    toBeRemoved.add(v1);
                }
            }
            for (V v12 : toBeRemoved) {
                changed = true;
                user.persistentServices.remove(v12);
                user.services.remove(v12);
                notifyListener(v12, userId, true);
            }
            if (changed) {
                onServicesChangedLocked(userId);
                writePersistentServicesLocked(user, userId);
            }
        }
    }

    protected void onServicesChangedLocked(int userId) {
    }

    private boolean containsUid(int[] changedUids, int uid) {
        return changedUids != null ? ArrayUtils.contains(changedUids, uid) : true;
    }

    private boolean containsType(ArrayList<ServiceInfo<V>> serviceInfos, V type) {
        int N = serviceInfos.size();
        for (int i = 0; i < N; i++) {
            if (((ServiceInfo) serviceInfos.get(i)).type.equals(type)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsTypeAndUid(ArrayList<ServiceInfo<V>> serviceInfos, V type, int uid) {
        int N = serviceInfos.size();
        for (int i = 0; i < N; i++) {
            ServiceInfo<V> serviceInfo = (ServiceInfo) serviceInfos.get(i);
            if (serviceInfo.type.equals(type) && serviceInfo.uid == uid) {
                return true;
            }
        }
        return false;
    }

    protected ServiceInfo<V> parseServiceInfo(ResolveInfo service) throws XmlPullParserException, IOException {
        ServiceInfo si = service.serviceInfo;
        ComponentName componentName = new ComponentName(si.packageName, si.name);
        PackageManager pm = this.mContext.getPackageManager();
        XmlResourceParser parser = null;
        try {
            parser = si.loadXmlMetaData(pm, this.mMetaDataName);
            if (parser == null) {
                throw new XmlPullParserException("No " + this.mMetaDataName + " meta-data");
            }
            AttributeSet attrs = Xml.asAttributeSet(parser);
            int type;
            do {
                type = parser.next();
                if (type == 1) {
                    break;
                }
            } while (type != 2);
            if (this.mAttributesName.equals(parser.getName())) {
                V v = parseServiceAttributes(pm.getResourcesForApplication(si.applicationInfo), si.packageName, attrs);
                if (v == null) {
                    if (parser != null) {
                        parser.close();
                    }
                    return null;
                }
                ServiceInfo<V> serviceInfo = new ServiceInfo(v, service.serviceInfo, componentName);
                if (parser != null) {
                    parser.close();
                }
                return serviceInfo;
            }
            throw new XmlPullParserException("Meta-data does not start with " + this.mAttributesName + " tag");
        } catch (NameNotFoundException e) {
            throw new XmlPullParserException("Unable to load resources for pacakge " + si.packageName);
        } catch (Throwable th) {
            if (parser != null) {
                parser.close();
            }
        }
    }

    private void readPersistentServicesLocked(InputStream is) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, StandardCharsets.UTF_8.name());
        int eventType = parser.getEventType();
        while (eventType != 2 && eventType != 1) {
            eventType = parser.next();
        }
        if ("services".equals(parser.getName())) {
            eventType = parser.next();
            while (true) {
                if (eventType == 2 && parser.getDepth() == 2) {
                    if (Notification.CATEGORY_SERVICE.equals(parser.getName())) {
                        V service = this.mSerializerAndParser.createFromXml(parser);
                        if (service != null) {
                            int uid = Integer.parseInt(parser.getAttributeValue(null, GrantCredentialsPermissionActivity.EXTRAS_REQUESTING_UID));
                            findOrCreateUserLocked(UserHandle.getUserId(uid), false).persistentServices.put(service, Integer.valueOf(uid));
                        } else {
                            return;
                        }
                    }
                }
                eventType = parser.next();
                if (eventType == 1) {
                    return;
                }
            }
        }
    }

    private void migrateIfNecessaryLocked() {
        if (this.mSerializerAndParser != null) {
            File syncDir = new File(new File(getDataDirectory(), "system"), REGISTERED_SERVICES_DIR);
            AtomicFile oldFile = new AtomicFile(new File(syncDir, this.mInterfaceName + ".xml"));
            if (oldFile.getBaseFile().exists()) {
                File marker = new File(syncDir, this.mInterfaceName + ".xml.migrated");
                if (!marker.exists()) {
                    AutoCloseable autoCloseable = null;
                    try {
                        autoCloseable = oldFile.openRead();
                        this.mUserServices.clear();
                        readPersistentServicesLocked(autoCloseable);
                    } catch (Exception e) {
                        Log.w(TAG, "Error reading persistent services, starting from scratch", e);
                    } finally {
                        IoUtils.closeQuietly(autoCloseable);
                    }
                    try {
                        for (UserInfo user : getUsers()) {
                            UserServices<V> userServices = (UserServices) this.mUserServices.get(user.id);
                            if (userServices != null) {
                                writePersistentServicesLocked(userServices, user.id);
                            }
                        }
                        marker.createNewFile();
                    } catch (Exception e2) {
                        Log.w(TAG, "Migration failed", e2);
                    }
                    this.mUserServices.clear();
                }
            }
        }
    }

    private void writePersistentServicesLocked(UserServices<V> user, int userId) {
        if (this.mSerializerAndParser != null) {
            AtomicFile atomicFile = createFileForUser(userId);
            FileOutputStream fos = null;
            try {
                fos = atomicFile.startWrite();
                XmlSerializer out = new FastXmlSerializer();
                out.setOutput(fos, StandardCharsets.UTF_8.name());
                out.startDocument(null, Boolean.valueOf(true));
                out.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                out.startTag(null, "services");
                for (Entry<V, Integer> service : user.persistentServices.entrySet()) {
                    out.startTag(null, Notification.CATEGORY_SERVICE);
                    out.attribute(null, GrantCredentialsPermissionActivity.EXTRAS_REQUESTING_UID, Integer.toString(((Integer) service.getValue()).intValue()));
                    this.mSerializerAndParser.writeAsXml(service.getKey(), out);
                    out.endTag(null, Notification.CATEGORY_SERVICE);
                }
                out.endTag(null, "services");
                out.endDocument();
                atomicFile.finishWrite(fos);
            } catch (IOException e1) {
                Log.w(TAG, "Error writing accounts", e1);
                if (fos != null) {
                    atomicFile.failWrite(fos);
                }
            }
        }
    }

    protected void onUserRemoved(int userId) {
        synchronized (this.mServicesLock) {
            this.mUserServices.remove(userId);
        }
    }

    protected List<UserInfo> getUsers() {
        return UserManager.get(this.mContext).getUsers(true);
    }

    protected UserInfo getUser(int userId) {
        return UserManager.get(this.mContext).getUserInfo(userId);
    }

    private AtomicFile createFileForUser(int userId) {
        return new AtomicFile(new File(getUserSystemDirectory(userId), "registered_services/" + this.mInterfaceName + ".xml"));
    }

    protected File getUserSystemDirectory(int userId) {
        return Environment.getUserSystemDirectory(userId);
    }

    protected File getDataDirectory() {
        return Environment.getDataDirectory();
    }

    protected Map<V, Integer> getPersistentServices(int userId) {
        return findOrCreateUserLocked(userId).persistentServices;
    }
}