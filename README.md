# ConcatAdapterExtension

Extension for `ConcatAdapter` usage, now support `SpanSizeLookup` and `ItemDecoration` (only for spacing) feature.

## Install

Add [Jitpack](https://jitpack.io/) repository to your root `build.grable`:
```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Then add dependency in your module `build.gradle`:
```groovy
dependencies {
  implementation 'com.github.carousell:ConcatAdapterExtension:1.0.0'
}
```

## Usage

### SpanSizeLookup

Let all of your child `Adapter` extends `SpanSizeLookupOwner` and link to the `SpanSizeLookup` you want to have for each `Adapter`.
And then generate `ConcatSpanSizeLookup` by your `ConcatAdapter` and max span count.

```kotlin
val adapter = ConcatAdapter()
val layoutManager = GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false)
layoutManager.spanSizeLookup = ConcatSpanSizeLookup(adapter, spanCount)
recyclerView.layoutManager = layoutManager
```

### ItemDecoration

Let all of your child `Adapter` extends `ItemDecorationOwner` and link to the `ItemDecoration` you want to have for each `Adapter`.
And then generate `ConcatItemDecoration` by your `ConcatAdapter`.

```kotlin
val adapter = ConcatAdapter()
recyclerView.addItemDecoration(ConcatItemDecoration(adapter))
```
