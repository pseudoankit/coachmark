# Jetpack Compose Coachmark/Onboarding Library

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.pseudoankit/coachmark/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.pseudoankit/coachmark)
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>


A library for creating coachmarks or onboarding flows using Jetpack Compose.

<img src="https://github.com/pseudoankit/coachmark/assets/54987308/38c18ebb-5057-46f8-bdd8-6d9c966a603b" width="200" height="400"/>

## Overview

A lightweight jetpack compose library to create onboarding flow for your android app
Now provide seamless onboarding experience to end users with just few lines of code

## Features

- Create custom coachmarks with ease.
- Highly flexible and extensible.
- Compatible with Jetpack Compose UI components.

## Getting Started

In your module's build.gradle

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.pseudoankit/coachmark/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.pseudoankit/coachmark)
```
dependencies {
    implementation 'io.github.pseudoankit:coachmark:<latest_version🔝>'
}
```

## Usage

Define Keys for all coachmarks
```
enum class Keys { Text1, Text2 }
```

At root level make sure to wrap with UnifyCoachmark
```
UnifyCoachmark(
    tooltip = { /* Declare Tooltip Source code below ⏬ */ Tooltip(it) },
    overlayEffect = DimOverlayEffect(Color.Black.copy(alpha = .5f)),
    onOverlayClicked = { OverlayClickEvent.GoNext }
) {
    Content()     // Source code below ⏬
}
```

Enable coachmark for the required views with `enableCoachMark`, To access `enableCoachMark` you need to be inside `CoachmarkScope`
If you are not in `CoachmarkScope` then get access to it via LocalCoachMarkScope.current
```
@Composable
private fun Content() {
    with(LocalCoachMarkScope.current) {    // not needed if you are already in `CoachmarkScope`
        Text(
            text = "Will show tooltip 1",
            modifier = Modifier
                .enableCoachMark(
                    key = Keys.Text1,    // unique that we declared above
                    toolTipPlacement = placement,
                    highlightedViewConfig = HighlightedViewConfig(
                        shape = HighlightedViewConfig.Shape.Rect(12.dp),
                        padding = PaddingValues(8.dp)
                    )
                )
        )
    }
}
```

Define tooltip view (Tootip is showing when view is highlighted currently) 
```
@Composable
private fun Tooltip(key: CoachMarkKey) {
    when (key) {
        Keys.Text1 -> {
            Balloon(arrow = Arrow.Start()) {
                Text(text = "Highlighting Text1", color = Color.White)
            }
        }
    
        Keys.Text2 -> {
            Balloon(arrow = Arrow.Start()) {
                Text(text = "Highlighting Text2", color = Color.White)
            }
        }
    }
}
```


Inspired from <a href = "https://github.com/svenjacobs/reveal">reveal</a> library

# License
```xml
Copyright 2019 pseudoankit (Ankit)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
