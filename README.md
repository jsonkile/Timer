# ⏱️ Timer App

A simple android timer application that shows a list of running timers using clean architecture.

## 🎥 Demo

Here’s a quick video demonstration of the app:

<div align="center">
  <video src="https://github.com/user-attachments/assets/74c3ea75-be2d-4c05-b072-d6e7240183ae" controls="controls" style="width: 100%; max-width: 600px;">
    Your browser does not support the video tag. Please <a href="https://github.com/user-attachments/assets/74c3ea75-be2d-4c05-b072-d6e7240183ae">download the video</a> to view it.
  </video>
</div>

## 🛠️ How It Works

### Data
Each timer in the database stores:
- **Start Time**: The timestamp when the timer was started.
- **Total Stop Time**: The cumulative duration the timer was paused.
- **State**: The current state of the timer (stopped or started).
- The **current timer time** is calculated using the formula: Current Timer Time = Current Time - Start Time - Total Stop Time
- This ensures the timer accurately reflects the elapsed time, even if paused and restarted multiple times.

### UI 
- The timer count is handled on the UI using a `produceState` effect within the Composable.
- The state updates every **99ms**, ensuring smooth and precise visual feedback for users.


## 🛠️ Technologies Used

<ul>
  <li><strong>Programming Language:</strong> Kotlin</li>
  <li><strong>UI Framework:</strong> Jetpack Compose</li>
  <li><strong>Architecture:</strong> Room DB</li>
  <li><strong>Dependency Injection:</strong> Hilt</li>
</ul>
