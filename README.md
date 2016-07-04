# 高仿小米和魅族应用商店的下载按钮

![Screencast_2016-07-04-18-13-39.mp4_1467627442.gif](http://upload-images.jianshu.io/upload_images/767626-facc9474578dfb82.gif?imageMogr2/auto-orient/strip)
### 基本用法，布局文件添加自定义view
`
<com.lineprogressbutton.fewwind.myapplication.LineProgressButton
android:id="@+id/id_line_progerss_bar"    
android:layout_width="match_parent"    
android:layout_height="40dp"    
android:layout_centerInParent="true"   
android:paddingLeft="50dp"   
android:paddingRight="50dp"    
app:bg_loading_state_first="@android:color/darker_gray"
app:bg_loading_state_seconed="@android:color/holo_blue_bright"
app:bg_nromal_state="@android:color/holo_red_light"    
app:text_color="@android:color/white"    
app:text_content_pre="下载"    
app:text_size="18sp">
</com.lineprogressbutton.fewwind.myapplication.LineProgressButton>

### 支持的基本属性

    <!--带进度的button-->
    <declare-styleable name="CustomLineProgressBar">
        <!--正常状态下vew的背景-->
        <attr name="bg_nromal_state" format="color"></attr>
        <!--下载过程中未下载部分的背景-->
        <attr name="bg_loading_state_first" format="color"></attr>
        <!--下载过程中已下载的颜色-->
        <attr name="bg_loading_state_seconed" format="color"></attr>
        <!--中间文本的颜色-->
        <attr name="text_color" format="color"></attr>
        <!--中间文本的大小-->
        <attr name="text_size" format="dimension"></attr>
        <!--中间文本未下载显示文字-->
        <attr name="text_content_pre" format="string"></attr>

    </declare-styleable>
