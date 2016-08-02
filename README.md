#RxLuban

a picture compaction algorithm util Rx Version

depend on [Luban](https://github.com/Curzibn/Luban)

##AndroidStudio
```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

```
dependencies {
	        compile 'com.github.kHRYSTAL:RxLuban:v0.0.1'
	}
```

##Eclipse
![image](https://github.com/kHRYSTAL/RecyclerViewAdapterDelegate/blob/master/screenshot/hehe.jpeg)

##Usage

eg.

```java
RxLuban.getInstance(context)
       .load(filepath)
       .putGear(RxLuban.THIRD_GEAR) //compress gear
       .launch(new HandleCallback<File>() {
            @Override
            public void beforeHandle() {
                Log.d(TAG, "beforeHandle");
            }

            @Override
            public void handleError(String msg) {
                Log.d(TAG, "handleError" + msg);
            }

            @Override
            public void handleComplete() {
                Log.d(TAG, "handleComplete");
            }

            @Override
            public void handleSuccess(File data) {
                //handle result file
            }
        });
```







##Thanks
*   [Curzibn](https://github.com/Curzibn)