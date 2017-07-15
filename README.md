# RuntimePermissionUtil-Android

RuntimePermissionUtil-Android is a simple runtime permission util lib which provides simple callback's whenver 
user grants/denies a permission.Based on user action developer can perform the functionality.

# Contributing  
Please raise an issue of the requirement so that a discussion can take before any code is written, 
even if you intend to raise a pull request.

Please see Sample app for full example.

# Gradle confifuration 

## Step 1. Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

## Step 2. Add the dependency

```gradle
dependencies {
	        compile 'com.github.CammyKamal:RuntimePermissionUtil-Android:0.1.0'
	}
```

# Java Code Snippet

```java
     
      RuntimePermissions runtimePermissions;
      
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runtimePermissions = new RuntimePermissions(MainActivity.this, Permission.CALL_PHONE_PERMISSION, new PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Granted", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionDenied() {
                Toast.makeText(MainActivity.this, "Denied", Toast.LENGTH_LONG).show();
            }
        });
        runtimePermissions.askUserPermissionGranted();
    }
    
    /**
     * need for Android 6+ real time permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        runtimePermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

```

```
MIT License

Copyright (c) [2017] [Kamal Vaid]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
