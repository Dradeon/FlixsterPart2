## Flix Part 2

### User Stories

#### REQUIRED (10pts)

- [X] (8pts) Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- [X] (2pts) Allow video posts to be played in full-screen using the YouTubePlayerView.

#### BONUS

- [X] Implement a shared element transition when user clicks into the details of a movie (1 point).
- [X] Trailers for popular movies are played automatically when the movie is selected (1 point).
  - [X] When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
  - [ ] Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- [X] Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
- [ ] Apply data binding for views to help remove boilerplate code. (1 point)
- [ ] Add a rounded corners for the images using the Glide transformations. (1 point)

### App Walkthough GIF

<img src="http://g.recordit.co/SKnTbsza3C.gif" width=250><br>

### Notes

I had trouble finding a specific library to do the Shared Element transition though I solved the problem after doing some research.

## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

# Flixster  Part 1

A app that displays a list of movies with their posters and overview. If a movie has an rating higher than 8, the backdrop is displayed instead.

Submitted By: Bakhtiar Reza

Time spent: 3 hours

## User Stories

The following **required** functionality is completed:

* [X] User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

The following **optional** features are implemented:

* [X] Views should be responsive for both landscape/portrait mode.
* [ ] Display a nice default placeholder graphic for each image during loading. If you're running your app on an emulator, you can change the network speed of the emulator to test this feature by following the guidance here. 
* [ ] Improve the user interface through styling and coloring
* [X] For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.
## Video Walkthrough

<img src='http://g.recordit.co/GuDo3SwL8w.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [recordit](https://recordit.co/).
