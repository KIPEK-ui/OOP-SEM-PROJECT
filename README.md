
# Examination Processing System

## Overview
This project is a **Java-based application** designed to process examination results for a university department. It provides functionalities for adding students, managing grades, and viewing dynamically generated report cards using a user-friendly GUI.

## Features
1. **Add Students**:
   - Users can input student names and IDs via a dialog box.
   - Students are dynamically added to the system.

2. **Add Grades**:
   - Users can input scores for predefined subjects.
   - Only students without grades are listed for grade entry, avoiding duplication.

3. **View Report Cards**:
   - Allows users to select a student from a dropdown menu to view their report card.
   - The report card includes:
     - Student name and ID.
     - Scores for all subjects.
     - Average score and grade.
     - Recommendation based on performance.
     - Date of report generation.

## Subjects
- Human-Computer Interaction
- Software Engineering
- Database Systems
- Operating Systems
- Artificial Intelligence

## Technologies Used
- **Java Swing**: For creating graphical user interfaces (GUI).
- **Java Collections** (`ArrayList`, `HashMap`): For dynamic data handling.
- **Java AWT**: For layout management.
- **Java Utilities**: For managing data and formatting dates.

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone <repository_link>
   ```
2. Open the project in your preferred Java IDE (e.g., Eclipse, IntelliJ IDEA).
3. Compile and run the `ExaminationProcessingGUI.java` file.
4. Follow the instructions displayed on the GUI.

## Collaboration
This project involves **three collaborators**. Below are their GitHub usernames:
- **Collaborator 1**: [Emmanuel Keter](https://github.com/KIPEK-ui)
- **Collaborator 2**: [Crystal Muthui](https://github.com/Crystalmuthui)
- **Collaborator 3**: [Ian Cecil](https://github.com/Iancecil)

### Contribution Guidelines
- Create a new branch for each feature/fix:
  ```bash
  git checkout -b feature-branch-name
  ```
- Commit regularly with clear messages:
  ```bash
  git commit -m "Added functionality for viewing report cards"
  ```
- Push your branch and create a pull request for review.
- Ensure code quality by adhering to the following principles:
  - Follow Java naming conventions.
  - Write clear and concise comments.
  - Avoid hardcoding values where feasible.

### Submission
Ensure the repository is public and includes the following:
1. Source code.
2. README.md file (this file).
3. Names of the collaborators.

## Strathmore University Grading System
| Average Score Range | Grade | Recommendation       |
|---------------------|-------|----------------------|
| 70 - 100           | A     | Excellent           |
| 60 - 69            | B     | Good                |
| 50 - 59            | C     | Fair                |
| 40 - 49            | D     | Poor                |
| Below 40           | F     | Very Poor           |
