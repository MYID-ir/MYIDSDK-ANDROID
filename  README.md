![](RackMultipart20210529-4-xwtllc_html_f72a2a50906a1676.png)

**راهنمای**  **ANDROID SDK**  **مای آیدی**

**نسخه**  **1.0**

برای استفاده از ANDROID SDK مای آیدی مراحل زیر را در پروژه‌ی خود اعمال کنید:

**بخش اول نصب برروی پروژه**  **:**

1. فایل‌های [MYIDSDK\_1\_0.aar](https://cdn.myid.ir/sdk/MYIDSDK_1_0.aar)در پوشه libs مربوط به ماژول app کپی کنید.

2. در فایل build.gradle مربوط به ماژول app در بخش {}android مقادیر زیر را طبق نمونه تکمیل نمایید.

نکته : توجه داشته باشید این تغییر نسخه جاوا ماژول شما را به java 8 ارتقا می‌دهد.

compileOptions {
sourceCompatibility = 1.8
targetCompatibility = 1.8
}

توجه داشته باشید این نسخه فقط با targetSdkVersion 29 میباشد

3. در فایل بالا ، بخش {}dependencies ، کتابخانه‌های پایین رو اضافه کنید:

implementation (name: &#39;MYIDSDK\_1\_0&#39;, ext:&#39;aar&#39;)


implementation &#39;com.mikhaellopez:circularprogressbar:3.0.3&#39;
implementation &#39;com.loopj.android:android-async-http:1.4.9&#39;
implementation &quot;com.google.android.gms:play-services-base:17.5.0&quot;

4. در فایل build.gradle مربوط به project مطابق زیر تغییرات را اعمال نمایید:

allprojects **{**
repositories **{**
google()
 mavenCentral()
 jcenter()

flatDir **{**
dirs &#39;libs&#39;
**}

 }
 }**

5. دسترسی های زیر را به مانیفست برنامه اضافه کنید :

\&lt;uses-permission android:name=&quot;android.permission.WRITE\_EXTERNAL\_STORAGE&quot; /\&gt;
 \&lt;uses-permission android:name=&quot;android.permission.MANAGE\_EXTERNAL\_STORAGE&quot; /\&gt;
 \&lt;uses-permission android:name=&quot;android.permission.READ\_EXTERNAL\_STORAGE&quot;/\&gt;
 \&lt;uses-permission android:name=&quot;android.permission.INTERNET&quot; /\&gt;
 \&lt;uses-permission android:name=&quot;android.permission.CAMERA&quot; /\&gt;
 \&lt;uses-feature android:name=&quot;android.hardware.camera&quot; /\&gt;

و به تگ APPLICATION

android:requestLegacyExternalStorage=&quot;true&quot;

را اضافه کنید.

مثل:

\&lt;application
android:requestLegacyExternalStorage=&quot;true&quot;


android:allowBackup=&quot;true&quot;
android:icon=&quot;@mipmap/ic\_launcher&quot;
android:label=&quot;@string/app\_name&quot;
android:roundIcon=&quot;@mipmap/ic\_launcher\_round&quot;
android:supportsRtl=&quot;true&quot;
android:theme=&quot;@style/Theme.MYIDSDKDEMO&quot;\&gt;

6. سپس فرمان sync گریدل را اجرا نمایید.

**بخش دوم استفاده از**  **sdk**

ابتدا کلاس سازنده مای آیدی را صدا زده

MyidAi myidSDK = new MyidAi(this, &quot;KEY&quot;,&quot;TOKEN&quot;);

پارامتر ها

| **نام** | **توضیحات** |
| --- | --- |
| **KEY** | شما میتوانید KEY را از پنل مای آیدی خود در بخش apiتوسعه دهندگان برای پروژه خود ایجاد نمایید.
 |
| **TOKEN** | شما باید درخواست ایجاد توکن را که در بخش سوم همین راهنما آمده بخوانید. |

برای استفاده از تطابق تصویر کارت ملی و liveness

myidSDK.cardLiveness(&quot;REFERENCE\_ID&quot; ,new ResultListener() {
@Override
public void onResult(Object Value) {

}
 }
 );

وبرای livenessبه تنهایی

myidSDK.liveNess(&quot;REFERENCE\_ID&quot;,new ResultListener() {
@Override
public void onResult(Object Value) {

}
 }
 );

پارامتر ها

| **نام** | **توضیحات** |
| --- | --- |
| **REFERENCE\_ID** | کد رفرنس شما برای درخواست |

خروجی value میتواند یکی از موارد زیر باشد.

| **نام** | **توضیحات** |
| --- | --- |
| **Success** | تطابق و liveness صحیح میباشد |
| **Fail** | خطا |
| **SendError** | خطا |
| **FaceNotFind** | خطا |
| **LivenessINITError** | خطا |
| **INTERNETError** | خطا |
| **FaceNotInRect** | خطا |
| **TimeOut** | خطا |
| **FaceVerifyError** | خطا |
| **FaceVerification\_FaceNotFound1** | خطا |
| **FaceVerification\_FaceNotFound2** | خطا |
| **FaceVerification\_NotVERIFY** | خطا |
| **FaceVerification\_BelongDifferentPerson** | خطا |
| **FaceVerification\_BelongSamePerson** | خطا |

برای دریافت TOKEN باید در برنامه تابع زیر را صدا بزنید

توجه داشته باشید که تاریخ انقضای tokenسی روزه می باشد و باید هر سی روز یک توکن جدید دریافت کنید.

آدرس ارسالی : [http://service.myid.ir/api/v1/token](http://service.myid.ir/api/v1/token)

پارامتر های ارسالی :

| **نام** | **توضیحات** |
| --- | --- |
| **Authorization** | شما میتوانید KEY را از پنل مای آیدی خود در بخش apiتوسعه دهندگان برای پروژه خود ایجاد نمایید. |

پارامتر های دریافتی :

| **نام** | **توضیحات** |
| --- | --- |
| **package\_name** | نام پکیج ثبت شده شما در لیست api ها |
| **Token** | تو کن قابل استفاده |
| **expired\_at** | زمان انقضای توکن |

5

[MYID.IR](http://MYID.IR/)