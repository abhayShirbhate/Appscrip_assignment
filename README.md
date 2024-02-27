# Android Mock Test Project

## Overview
This repository contains an Android application developed as part of a mock test. The application showcases various features including displaying a list of users fetched from a paginated API, allowing users to mark users as favorites locally using swipe functionality, and implementing a home page with bottom navigation.

## Features
1. **List of Users:**
   - Fetches a list of users from a paginated API.
   - Displays users with their Name, Gender, and Status (active or in-active).
   - Indicates the status of each user with a round icon in the top left corner. Active users have a green icon, while in-active users have a red icon.
   - List item decorations, fonts, and styles can be customized.

2. **Make a User Your Favourite (Local Only):**
   - Allows users to mark a user as a favorite locally using swipe functionality in the list.
   - No API calls needed for this feature.
   - Swipe left on a list item to reveal an area with an option to add the user to favorites.
   - Upon tapping "Add to Favorite", the user is marked as a favorite, and a message with an icon and text ("Added") is displayed below the list item for 3 seconds.
   - After 3 seconds, if the user swipes the same item again, the option changes to "Remove from Favorite". Tapping it displays a message ("Removed") for 3 seconds.

3. **Home Page UI:**
   - Features a home page with bottom navigation.
   - Bottom Navigation includes 4 menu items: Users, Favorite, To-Do, Profile.
   - Users: Displays all users.
   - Favorite: Displays all favorite users.
   - To-Do and Profile: Placeholder pages for future development.

## How to Run the Application
1. Clone this repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on an Android emulator or device.

## Acknowledgments
This project was created as part of a mock test.

---
**Note**: This README provides an overview of the Android Mock Test Project repository and its features. Feel free to explore the codebase for implementation details and customization options.
