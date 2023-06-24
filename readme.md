# Music Advisor

Welcome to Music Advisor! This project is a music advisor application built using Spring Boot, Java Gson, and Maven. It interacts with the Spotify API and operates in the console environment. With Music Advisor, you can explore music genres, get recommendations, and discover new songs, albums, and playlists based on your preferences.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Technologies](#technologies)
- [Contributing](#contributing)
- [License](#license)

## Installation

To set up and run the Music Advisor application locally, please follow these steps:

1. Clone the repository to your local machine:

```bash
git clone https://github.com/VhugoJc/musicAdvisor.git
```
2. Navigate to the project directory:
```bash
cd musicAdvisor
```
3. Obtain API credentials:
Visit the [Spotify Developer Dashboard](https://developer.spotify.com/dashboard) and create a new application to obtain your client ID and client secret.
Set the following environment variables with your credentials:
`SPOTIFY_CLIENT_ID`: Your Spotify application's client ID.
`SPOTIFY_CLIENT_SECRET`: Your Spotify application's client secret.
4. Run the application:
```bash
mvn spring-boot:run
```
The application will run in the console.

## Usage
Once the application is running, you can interact with it using the console. The application provides the following commands:

`auth`: Authenticates with Spotify (you will need to authorize the application with your Spotify account).
`featured`: Displays a list of Spotify's featured playlists.
`new`: Displays a list of new album releases.
`categories`: Displays a list of available categories on Spotify.
`playlists` <category_name>: Displays a list of playlists in the specified category.
`exit`: Exits the application.
To use a command, simply type it in the console and press Enter. Follow the prompts and instructions to navigate through the application.

## Features
- Authenticate with Spotify to access personalized recommendations.
- Browse featured playlists, new album releases, and available - categories.
- View playlists within specific categories.
- Interact with the application through the console environment.

## Technologies
The Music Advisor application is built using the following technologies:

**Spring Boot**: A Java framework for building web applications.
**Java Gson**: A Java library for handling JSON data.
**Maven**: A build automation and dependency management tool for Java projects.
## Contributing
Contributions to the Music Advisor project are always welcome! If you have any ideas, suggestions, or bug reports, please open an issue on the GitHub repository.

If you'd like to contribute code, you can fork the repository, make your changes, and submit a pull request. Please ensure that your code follows the project's coding style and that you've added appropriate tests.

## License
The Music Advisor project is licensed under the MIT License. Feel free to use, modify, and distribute the code as per the terms of the license.

