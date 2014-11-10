# Color Picker Library

You can use this for changing colors of text or backgrounds.

  - extends PopupWindow
  - you can set cordinats 
  - you can get colors shades

### Usage
  - clone repository and add sources into your project 
  - add this line into dependencies your gradle build file
  
```
compile project(':color_pickers_library')
```


```
ColorPicker picker = new ColorPicker.ColorPickerBuilder(this)
                .colors(getMainColors()).setHolderView(textView)
                .colorsListener(this)
                .backgroundColor(getResources().getDrawable(android.R.color.holo_blue_light))
                .animation(true)
                .itemType(ColorPicker.ItemType.SHAPE)
                .build();
 ```
 
- ``` colrors(List<Integer> mainColors) ``` - list of main colors 
- ``` setHolderView(View view) ``` - set the view on which to pin the popup window
- ``` colorsListener(this)``` - your Activity has to implement **ColorSelectedListener**, to get the color
- ``` backgroundColor(Drawable drawable)``` - set Background drawable for your popup window
- ``` itemType(ColorPicker.ItemType type) ``` - set cells type (circle or shape)

### Version
0.1
Basic functionality



