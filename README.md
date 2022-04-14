# Duolingo Button Android


## What It Is

The famous DuoLingo button full functionality - with various theme customizations: foreground color, shadow color, button radius, text customizations, left image, and more!


## Importing the Library

On your root `build.gradle`, add `mavenCentral()` to the `allprojects` section.
On your module `build.gradle`, add

    dependencies {
        implementation 'in.tilicho.android:duolingobutton:1.0.0'
    }
    
## Features/Usage


```xml
<in.tilicho.android.duolingobutton.DuolingoButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="24dp"
    app:buttonCornerRadius="8"
    android:text="Hello World!"
    android:textColor="@color/black"
    app:buttonTextSize="16"
    app:buttonPaddingBottom="13"
    app:buttonPaddingEnd="10"
    app:buttonPaddingTop="10"
    app:buttonPaddingStart="13"
    
    app:primaryColor="@color/colorDefaultPrimary"
    app:shadowColor="@color/colorDefaultShadow"
    />


```

### XML attributes
If you add the view to your XML layout you have the possibility to set a few custom attributes, to customize the view's look. This can also be done programmatically.

1.  `buttonPaddingStart` - Padding start 
2.  `buttonPaddingTop` - Padding top
3.  `buttonPaddingEnd` - Padding end
4.  `buttonPaddingBottom` - Padding bottom
5.  `android:text` - Button text
6.  `buttonTextSize` - Size of text
7.  `android:textColor` - color of text
8.  `buttonCornerRadius` - Corner radius of button
9.  `primaryColor` - Button foreground color
10. `shadowColor` - Button shadow color
11. `icon` - Left icon of the button
12. `iconSpacer` - Space between icon and text


## Licence

MIT License

Copyright (c) 2022 Tilicho Labs

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
