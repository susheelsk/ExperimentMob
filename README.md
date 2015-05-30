# ExperimentMob
# ExperimentMob #
>This repository contains code for ExperimentMob. You can use *ExperimentMob* to implement A/B Testing in your app/game. You can find ExperimentMob-androidSDK [here](https://github.com/callmesusheel/ExperimentMob-androidSDK).

##Features##
* **Dashboard**. With ExperimentMob you get a beautiful dashboard with which you can set up experiments with ease. You can see which experiments are live and you can see which experiments were live previously. 
![Example](https://raw.githubusercontent.com/callmesusheel/ExperimentMob/master/screenshots/experiment-list.png)
* **Data Modeling**. You can create variables that can take on new values from the server. This allows you to roll out changes without having to push an update through Google Play. 
 ![Example](https://raw.githubusercontent.com/callmesusheel/ExperimentMob/master/screenshots/variables_dec.png)
 ![Example](https://raw.githubusercontent.com/callmesusheel/ExperimentMob/master/screenshots/fields.png)
* **Resource Synchronisation**. This system provides a way to automatically swap out your resources on the fly. Using this feature you can change images/sound files on the fly. No need to push out an update for new resources. 
* **User Filtering**. You can setup an experiment targetting a specific users based on various filters. These filters can be *application version, OS version, specific percentage of people, location(no location permissions needed), or cohorts*.
![Example](https://raw.githubusercontent.com/callmesusheel/ExperimentMob/master/screenshots/experiment-color.png)
![Example](https://raw.githubusercontent.com/callmesusheel/ExperimentMob/master/screenshots/experiment-file.png)
* **User Cohorts**. Using this feature you can create cohorts of a specific list of users whom you want to target in an experiment. This can be a list of paid users, or list of testers, list of beta users etc. All you have to do is create a file with each of these user ids in a new line and upload it. 
![Example](https://raw.githubusercontent.com/callmesusheel/ExperimentMob/master/screenshots/cohorts.png)
* **Multiple Apps**. ExperimentMob supports multiple apps and there is no limit to the number of apps. If yours is a studio producing multiple apps, you're covered by using just instance of ExperimentMob. 
![Example](https://raw.githubusercontent.com/callmesusheel/ExperimentMob/master/screenshots/apps.png)
* **History**. At any point you can check what experiments were live before they were removed, and for the duration they were live. 
![Example](https://raw.githubusercontent.com/callmesusheel/ExperimentMob/master/screenshots/history.png)

##Installation##
* ExperimentMob is built as a *maven* project. In order to build or run the project you'll need maven installed on your machine. To find maven installation details you can head [here](http://maven.apache.org/download.cgi).
* ExperimentMob requires Redis to store data. You'll need to install Redis on your machine and then start the redis server. You can find installation details [here](http://redis.io/download). Make sure you start the redis server before proceding any further.
* Now you've to edit the file "*config.properties*" in the root folder of the project. Edit the values of these lines in the file. The first property is the hostname of the machine where you're running redis. If you're running redis on the same machine as the ExperimentMob then hostname is usually localhost. The second property is the port which is bounded by Redis. The third property is the directory where the files will be written to by ExperimentMob. 
			redis-hostname=localhost
			redis-port=6379
			filespath=files/

* You're ready to start the ExperimentMob console now. Navigate to the root folder of the project and run the following command. 
> mvn jetty:run
* Go to *http://your-hostname:port/experimentmob/auth/login/* on your browser. You must find a login webpage. Now you've add an admin user and an app to get started. You can do that by going to *http://your-hostname:port/experimentmob/auth/install/*. 

> For further help or feedback, contact [Susheel](mailto:susheel.s2k@gmail.com) . Also, you can post the bugs on github. 
