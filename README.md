# Settings Screen – Jetpack Compose (Material 3)

## Overview

This project implements a polished **Settings screen** using **Jetpack Compose with Material 3 components**. The screen demonstrates proper layout structure, consistent spacing, and interactive UI elements. The layout uses a main `Column` container with multiple `Card` sections, each containing structured setting rows. Interactive components include switches, checkboxes, sliders, buttons, and a Snackbar confirmation.

## Layout Structure

- The main layout container is a `Column`
- Each setting item is implemented as a `Row`
  - Left side: `Column` (label + supporting text)
  - Right side: interactive control
- `Modifier.weight(1f)` is used to prevent truncation and keep controls aligned
- The screen is vertically scrollable using `verticalScroll()`

## 🎨 Material 3 Components Used

The implementation includes the following Material 3 components:

- TopAppBar  
- Card  
- Switch  
- Checkbox  
- Slider  
- Divider  
- AssistChip  
- IconButton  
- ListItem  
- Snackbar  

## Modifier Usage Demonstrated

The screen demonstrates correct usage of:

- `padding`
- `fillMaxWidth`
- `weight`
- `heightIn`
- `widthIn`
- `align`
- `clickable`
- `background`
- `border`
- `clip`
- `verticalScroll`
- `navigationBarsPadding`

These modifiers ensure consistent layout behavior, alignment, spacing, and responsiveness.

## 📸 Screenshots

Below are the required screenshots:

1. **Full Settings Screen**
   - Shows TopAppBar, Cards, and grouped settings.

2. **Scroll Behavior**
   - Demonstrates vertical scrolling and visibility of the Save button.

3. **Snackbar Confirmation**
   - Shows “Settings saved” after pressing the Save button.

4. **Slider Interaction**
   - Displays adjusted slider position.

## ▶ How to Run
1. Open the project in Android Studio  
2. Ensure an emulator (API 34+) is available  
3. Run `MainActivity`  
4. Interact with switches, slider, and Save button  


## AI Disclosure

