# ConcatAdapterExtension

[![CircleCI](https://circleci.com/gh/carousell/ConcatAdapterExtension.svg?style=shield)](https://circleci.com/gh/carousell/ConcatAdapterExtension)
[![](https://jitpack.io/v/carousell/ConcatAdapterExtension.svg)](https://jitpack.io/#carousell/ConcatAdapterExtension)

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
  implementation 'com.github.carousell:ConcatAdapterExtension:1.1.0'
}
```

## Usage

### SpanSizeLookup

Let all of your child `Adapter` extends `SpanSizeLookupOwner` and link to the `SpanSizeLookup` you want to have for each `Adapter`.
And then generate `ConcatSpanSizeLookup` by your `ConcatAdapter` and max span count.

```kotlin
val adapter = ConcatAdapter()
val layoutManager = GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false)
layoutManager.spanSizeLookup = ConcatSpanSizeLookup(spanCount) { adapter.adapters }
recyclerView.layoutManager = layoutManager
```

### ItemDecoration

Let all of your child `Adapter` extends `ItemDecorationOwner` and link to the `ItemDecoration` you want to have for each `Adapter`.
And then generate `ConcatItemDecoration` by your `ConcatAdapter`.

```kotlin
val adapter = ConcatAdapter()
recyclerView.addItemDecoration(ConcatItemDecoration { adapter.adapters })
```
Also please noted if you want to get the position of view inside Adapter, you should call getBindingAdapterPosition instead of getChildAdapterPosition because ConcatAdapter will be the middle layer and the position is not expected.
```kotlin
// This will not get the correct index.
final int adapterPosition = parent.getChildAdapterPosition(view);

// Please use this instead
final RecyclerView.ViewHolder holder = parent.getChildViewHolder(view);
final int adapterPosition = holder.getBindingAdapterPosition();
```
Go to ./app module for more information.

## Contributing

Thank you for being interested in contributing to this project. Check out the [CONTRIBUTING](https://github.com/carousell/ConcatAdapterExtension/blob/master/CONTRIBUTING.md) document for more info.

## About

<a href="https://github.com/carousell/" target="_blank"><img src="https://avatars2.githubusercontent.com/u/3833591" width="100px" alt="Carousell" align="right"/></a>

**ConcatAdapterExtension** is created and maintained by [Carousell](https://carousell.com/). Help us improve this project! We'd love the [feedback](https://github.com/carousell/ConcatAdapterExtension/issues) from you.

We're hiring! Find out more at <http://careers.carousell.com/>

## License

**ConcatAdapterExtension** is released under Apache License 2.0.
See [LICENSE](https://github.com/carousell/ConcatAdapterExtension/blob/master/LICENSE) for more details.
