# Football Club

Football Club is an Android application built with Kotlin and Jetpack Compose. It allows users to search for football clubs, view club and league information, and manage football leagues using a local database. The app uses TheSportsDB API for live football club data and stores league data from a bundled JSON file.

## Features

- **Search Football Clubs:**  
  Search for football clubs by name or by league using TheSportsDB API.

- **View Club Details:**  
  See detailed information about each club, including name, league, stadium, logo, and more.

- **Manage Leagues:**  
  Load a list of football leagues from a local JSON file (`football_leagues.json`) and store them in a local Room database.

- **Modern Android Stack:**  
  - Jetpack Compose for UI
  - Room for local database
  - Kotlin Coroutines for asynchronous operations
  - Coil for image loading
  - Material 3 design components

- **Responsive UI:**  
  Adapts to both portrait and landscape orientations.

## Getting Started

### Prerequisites

- Android Studio (Giraffe or newer recommended)
- Android SDK 24 or higher
- Internet connection (for club search functionality)

### Building the Project

1. **Clone the repository:**
   ```bash
   git clone <your-repo-url>
   cd Football_Club
   ```

2. **Open in Android Studio:**  
   Open the project folder in Android Studio.

3. **Build and Run:**  
   - Connect an Android device or start an emulator.
   - Click "Run" or use `Shift+F10`.

## Project Structure

- `app/src/main/java/com/example/football_club/`
  - `MainActivity.kt` - App entry point and navigation.
  - `AddLeagues.kt` - UI and logic for adding leagues from JSON to the database.
  - `SearchAllClubs.kt` - Search for clubs by name.
  - `SearchClubByLeague.kt` - Search for clubs by league.
  - `SearchClubs.kt` - UI for displaying club details.
  - `Clubs.kt` - Data class for club entities.
- `app/src/main/res/raw/football_leagues.json` - Local JSON file with league data.

## API Usage

The app uses [TheSportsDB API](https://www.thesportsdb.com/api.php) to fetch football club data. No API key is required for basic usage.

## License

This project is for educational purposes.

## References

- Patel, K. (2024). Handling Screen Orientation Changes in Jetpack Compose. [LinkedIn](https://www.linkedin.com/pulse/handling-screen-orientation-changes-jetpack-compose-kaleem-patel-iztuf/)

---

*Feel free to contribute or suggest improvements!*
