# PokerProjectCapstone
Poker probability calculator for UMUC capstone

## Dependencies -- managed with Maven

* HSQLDB
* JUnit (with EasyMock)
* Checkstyle

## Development process

Clone the repository locally first.

1. Create a new feature branch off of the current master branch. Name the branch something that describes the feature, such as `game-assets-support`
2. Make your changes in your feature branch. Commit messages should be descriptive of the change included. Try to squash/fixup commits so that each commit contains one piece of functionality.
3. Run the full test suite to protect against regression.
4. Open a Pull Request (PR) on github with your branch against master. If context would be helpful for the review, include it in the PR description.
5. Ask for a review from someone else on the team. Reviewers should check over the code, ask for changes or add comments as necessary.
6. Ensure that your branch will merge cleanly into master. If there are conflicts, either merge or rebase master on your branch and then push the updated branch.
7. Once your PR is reviewed and approved, merge your feature branch into master and delete your remote feature branch (you can do this through the PR interface in github).

## Notes

* **Do not push directly to master.**
* Be careful with force-pushes. Rewriting history can cause issues for other people.
* Make sure all code adheres to the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html). Using a linter in your IDE is highly recommended. [Installation Instructions for IntelliJ and Eclipse](https://github.com/HPI-Information-Systems/Metanome/wiki/Installing-the-google-styleguide-settings-in-intellij-and-eclipse)
* Write tests to cover any new code (and any old code that isn't covered). **Run the full test suite before opening a PR. Ensure that all merged tests pass -- the test suite in master should never fail.**
* Check these [Git flight rules](https://github.com/k88hudson/git-flight-rules) if you need to untangle a git problem.

## Documentation

* In-code documentation should be written in a Javadoc-friendly style.

## Builds

* Builds should be done through Maven.