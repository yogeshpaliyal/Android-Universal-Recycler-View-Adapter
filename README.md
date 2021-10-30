
![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Fcom%2Fyogeshpaliyal%2Funiversal-adapter%2Fmaven-metadata.xml)

[![GitHub issues](https://img.shields.io/github/issues/yogeshpaliyal/Android-Universal-Recycler-View-Adapter?style=for-the-badge)](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/issues)
[![GitHub forks](https://img.shields.io/github/forks/yogeshpaliyal/Android-Universal-Recycler-View-Adapter?style=for-the-badge)](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/network)
[![GitHub stars](https://img.shields.io/github/stars/yogeshpaliyal/Android-Universal-Recycler-View-Adapter?style=for-the-badge)](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/stargazers)
[![GitHub license](https://img.shields.io/github/license/yogeshpaliyal/Android-Universal-Recycler-View-Adapter?style=for-the-badge)](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/blob/master/LICENSE)
[![Twitter](https://img.shields.io/twitter/url?style=for-the-badge&url=https%3A%2F%2Ftwitter.com%2Fyogeshpaliyal)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Fgithub.com%2Fyogeshpaliyal%2FAndroid-Universal-Recycler-View-Adapter)

# Android Universal Recycler Adapter

Tired of creating 100s of Adapters and View Models.
This Library will make it easy, No need to create a Adapter, ViewHolder for every list

![Cover Image](https://raw.githubusercontent.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/master/images/Universal%20Recycler%20View.jpg)

## 🤔 How?
Using Resource Pattern to find the status of the list and show view types according to that.


### Add Dependency repository to your build file: 
![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Fcom%2Fyogeshpaliyal%2Funiversal-adapter%2Fmaven-metadata.xml)


```groovy
dependencies {
    implementation 'com.yogeshpaliyal:universal-adapter:1.0.0'
}
```

## 🛠️ Used in this Project

* [List Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)
* [Async DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)
* [Resource & Status Util Classes](https://blog.mindorks.com/mvvm-architecture-android-tutorial-for-beginners-step-by-step-guide)


## 🤩 Features

- Content Listing (Supports Multiple View Types)
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
    


## 📚 For more info Check [Wiki](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki)
### General
- [Intro](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/Intro)  
- [Features & Logic Table](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/Features-&-Logic-Table)
- [Implementation](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/Implement)
- [Getting started](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/Getting-Started)
- [XML Variables](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/XML-Variables)


### Examples
- [Simple List](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/Simple-List)
- [Loading, Content , Error](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/Loading,-Content,-Error)

- [Multiple View Types](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/Multiple-View-Types)
    - [Example 1](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/Multiple-View-Types#example-1)
    - [Example 2](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/wiki/Multiple-View-Types#example-2)

## Feedback


Having any issue to get you are looking for, feel free to put your question in [Discussion Section](https://github.com/yogeshpaliyal/Android-Universal-Recycler-View-Adapter/discussions) and help us Improving this library and Documentation.
  
**Happy Coding 😁** 

