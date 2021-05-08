
[![](https://jitpack.io/v/yogeshpaliyal/Android-Universal-Recycler-View-Adapter.svg?style=flat-square)](https://jitpack.io/#yogeshpaliyal/Android-Universal-Recycler-View-Adapter)
[![GitHub issues](https://img.shields.io/github/issues/yogeshpaliyal/Android-Universal-Recycler-View-Adapter?style=for-the-badge)](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/issues)
[![GitHub forks](https://img.shields.io/github/forks/yogeshpaliyal/Android-Universal-Recycler-View-Adapter?style=for-the-badge)](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/network)
[![GitHub stars](https://img.shields.io/github/stars/yogeshpaliyal/Android-Universal-Recycler-View-Adapter?style=for-the-badge)](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/stargazers)
[![GitHub license](https://img.shields.io/github/license/yogeshpaliyal/Android-Universal-Recycler-View-Adapter?style=for-the-badge)](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/blob/master/LICENSE)
[![Twitter](https://img.shields.io/twitter/url?style=for-the-badge&url=https%3A%2F%2Ftwitter.com%2Fyogeshpaliyal)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Fgithub.com%2Fyogeshpaliyal%2FAndroid-Universal-Recycler-View-Adapter)
# Android Universal Recycler Adapter

Tired of creating 100s of Adapters and View Models.
This Library will make it easy, No need to create a Adapter, ViewHolder for every list

## How?
Using Resource Pattern to find the status of the list and show view types according to that.


### Step #1. Add the JitPack repository to your build file:
```gradle
allprojects {
    repositories {
	...
    	maven { url "https://jitpack.io" }
    }
}
```
     
### Step #2. Add the dependency ([See latest release](https://jitpack.io/#yogeshpaliyal/Android-Universal-Recycler-View-Adapter)). 
[![](https://jitpack.io/v/yogeshpaliyal/Android-Universal-Recycler-View-Adapter.svg)](https://jitpack.io/#yogeshpaliyal/Android-Universal-Recycler-View-Adapter)

```groovy
dependencies {
    implementation 'com.github.yogeshpaliyal:Android-Universal-Recycler-View-Adapter:+'
}
```

## Used in this Project

* [List Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)
* [Async DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)
* [Resource & Status Util Classes](https://blog.mindorks.com/mvvm-architecture-android-tutorial-for-beginners-step-by-step-guide)
## Features

- Normal Listing
- Loading + Listing (Showing shimmer items for Loading then showing the actual Listing)
- Loading + LoadMore (Showing loading items and add a item at bottom to create infinite Listing)
- No Data Found
- Error Page
- Async DiffUtils for better performance    

  
    

### When to show which item?

Status|	List Size|	Result Behaviour
---|---|---
LOADING	|0	|Show Loading Cells (default 5 items)
SUCCESS	|0	|No Record Found Layout will be displayed
ERROR	|0|	Error Layout Shown
LOADING	|more than 0|	Data Cells + load more at end
SUCCESS	|more than 0|	Data Cells
ERROR	|more than 0|	Data Cells + error cell at end
  
## XML Variables

To bind your view item with Adapter using dataBinding you have to create use these Variables
name must be as follows 

### `model` 
Type should be same as model you are passing to Adapter.
```xml
<variable
            name="model"
            type="com.techpaliyal.androidkotlinmvvm.model.BasicModel" />
```

### `listener` 
Type Any/Object, should be same as you passing while create Adapter.
```xml
<variable
            name="listener"
            type="com.techpaliyal.androidkotlinmvvm.model.BasicListener" />
```

  
## Show me the code

### Simple List

#### List item  ([File](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/blob/master/app/src/main/res/layout/item_simple.xml))
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="model"
            type="com.techpaliyal.androidkotlinmvvm.model.BasicModel" />
        <variable
            name="listener"
            type="com.techpaliyal.androidkotlinmvvm.listeners.BasicListener" />
    </data>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        android:padding="5dp"
        android:textSize="16sp"
        android:text="@{model.name}"
        android:onClick="@{()->listener.onClick(model)}"/>

</androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

#### Initialize Adapter ([File](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/blob/master/app/src/main/java/com/techpaliyal/androidkotlinmvvm/ui/activity/BasicListingActivity.kt))
```kotlin
 private val mAdapter by lazy {
        UniversalRecyclerAdapter.Builder<BasicModel>(
            lifecycleOwner = this,
            content = UniversalAdapterViewType.Content(
                R.layout.item_simple,
                listener = object : BasicListener<BasicModel> {
                    override fun onClick(model: BasicModel) {
                        Toast.makeText(this@BasicListingActivity, model.name, Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        ).build()
    }
```

#### Attach to adapter
  ```kotlin
binding.recyclerView.adapter = mAdapter.getAdapter()
  ```


  #### Update Data
  ```kotlin
mAdapter.updateData(Resource.success(list))
  ```
