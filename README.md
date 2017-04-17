MsGraph_Android_REST_API_Explorer
=================================

**Table of contents**

-	[Change History](#change-history)
-	[Device requirements](#device-requirements)
-	[Prerequisites](#prerequisites)
-	[Azure client application registration](#Azure-client-application-registration)
-	[Microsoft account application registration](#Microsoft-account-application-registration)
-	[Configure the project](#configure-the-project)
-	[Run the project](#run-the-project)
-	[Understand the code](#understand-the-code)
-	[Contributing](#contributing)
-	[Questions and comments](#questions-and-comments)
-	[Additional resources](#additional-resources)

Looking to build cool apps that help people work with their OneNote notebooks? Explore, learn, and understand the OneNote REST endpoints through the Microsoft Graph API by using the MS Graph OneNote REST API Explorer for Android. This sample lets you view and run the REST APIs that read, add, update, and delete OneNote entities such as notebooks, section groups, sections, and pages. The app lets you authenticate in two ways. You can authenticate using a Microsoft Account\** (MSA) to connect to OneNote on your personal OneDrive. Or you can authenticate using a work or school account to connect to OneNote on your organization's OneDrive for Business on Office 365.

\** The OneNote API endpoints currently support requests via a user authorized via an AAD (work or school) account. Support for access to the OneNote endpoints via an authenticated MSA account is upcoming.

You can explore the following operations for OneNote:

**Notebook**

-	Get a list of your notebooks
-	Get notebooks and expand notebook sections
-	Get a notebook by id
-	Get metadata for a notebook
-	Get notebooks by name
-	Get a sorted list of notebooks with metadata
-	Create a new notebook

**Section group**

-	Get a list of section groups
-	Get a list of all section groups in a notebook
-	Create a section group in a notebook
-	Create a section group in a section group

**Sections**

-	Get a list of sections in a notebook
-	Get a list of all sections
-	Get sections with a specific name
-	Get metadata of a section
-	Get sections by name
-	Get metadata for a section
-	Create a section in a notebook
-	Create a section in a section group

**Pages**

-	Post a simple page with HTML content
-	Post a page with an embedded image
-	Get pages with a specific title
-	Post a page with a snapshot of a web page
-	Delete a page
-	Append text to a page
-	Post a page with an Url snapshot
-	Get the pages in a section
-	Post pages with rendered attachments
-	Post a page with note tags
-	post a page with business card image text
-	Post a page with extracted web page text
-	Get a list of all pages
-	Get a paged list of pages
-	Get a sorted list of pages
-	Get the HTML contents of a page

Change History
--------------

April 2017 
- Requests now target the Microsoft Graph API.

August 2015 
- Add "Create section group" and "Create section in a section group" calls.

July 2015: 
- Initial release.

Device requirements
-------------------

To run the REST Explorer project, your device must meet the following requirements:* Android API level 23 or newer

### Prerequisites

To use the Microsoft Graph OneNote REST API Explorer for Android, you need the following: - The latest version of [Android Studio](http://developer.android.com/sdk/index.html). - The [Gradle](http://www.gradle.org) build automation system version 2.3.1 or later. - An Office 365 account. You can sign up for [an Office 365 Developer subscription](https://portal.office.com/Signup/Signup.aspx?OfferId=C69E7747-2566-4897-8CBA-B998ED3BAB88&DL=DEVELOPERPACK&ali=1#0) that includes the resources you need to start building Office 365 apps. - [Java Development Kit (JDK) 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). - A registered Azure application with a client id and redirect URI value. The application must have the following permissions: - View and modify OneNote notebooks in your organization - View and modify OneNote notebooks - Create pages in OneNote notebooks - Sign you in and read your profile - Access your organization's directory - A registered Microsoft application with a client id.

Azure client application registration
-------------------------------------

1.	Sign in to the [Azure Management Portal](https://manage.windowsazure.com), using your Azure AD credentials.

2.	Choose your Azure AD tenant by selecting your account in the top right corner of the page.

3.	In the left-hand navigation pane, choose **More Services**, click **App Registrations**, and click **New application registration**.

4.	On the **Create** page, specify **MS Graph OneNote REST API Explorer** for the application name and select **Native** for application type. Specify a **Redirect URI**, for this example, you can specify http://localhost/OneNoteRESTExplorer.

5.	Click **Create**.

6.	Click on the newly created app and copy the **Application ID**. You will need this value and the **Redirect URI** when configuring the application, in the **Getting the ClientID and RedirectUri into the project** section.

7.	From the settings page, select **Required Permissions**.

8.	Click on the **Windows Azure Active Directory** API.

9.	In the **Select Permissions** tab, under **Delegated Permissions**, select the following permissions:

	-	Sign in and read user profile
	-	Access the directory as the signed-in user

10.	Navigate back to the **Required Permissions** tab and click **Add**.

11.	Click **Select an API** and search for **Microsoft Graph**. Click **Select**.

12.	Under the **Select Permissions** tab, in the **Delegated permissions** section, add the following permissions:

	-	Read and write notebooks that the user can access
	-	View users' basic profile.

Microsoft account application registration
------------------------------------------

1.	Go to the [Windows Live application management site](http://go.microsoft.com/fwlink/?LinkID=144070).

2.	Sign in by using your Windows Live ID. >Note: If this is your first visit to this site, you will see several pages that configure your Windows Live ID for use with the site.

3.	Click **Add an app**.

4.	Enter a unique application name and **Create application**.

5.	There are many settings for your app, but to make it work with this sample, you need to make the following change:

	-	In the **Platforms** section, click **Add Platform** followed by **Mobile Application**.

6.	Once you are satisfied with your app settings, click **Save**.

7.	Take note of the **Application Id** listed near the top of the page. You will use this value later when configuring your project.

Configure the project
---------------------

1.	Download or clone the [Microsoft Graph OneNote REST API Explorer for Android](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer).

2.	Start Android Studio.

3.	From the **Welcome to Android Studio** dialog box, choose **Import project (Eclipse ADT, Gradle, etc)**.

4.	Select the **settings.gradle** file in the **MSGraph_OneNoteApiSampleAndroid** folder and click **OK**.

5.	Respond to the dialog ("Gradle Sync: Gradle settings for this project are not configured yet. Would you like the project to use the Gradle wrapper?") by clicking the **OK** button to use the Gradle wrapper.

6.	Open the ServiceConstants.java file in the app/java/com.microsoft.o365_android_onenote_rest/conf directory.

7.	Find the `CLIENT_ID` string and set its value to the Application ID for the application that you registered in Azure.

8.	Find the `REDIRECT_URI` string and set its value to the redirect URI for the application that you registered in Azure.

9.	Find the `MSA_CLIENT_ID` string and set its value to the Application ID for the application that you registered for your app in your Microsoft Account.

Run the project
---------------

Once you've built the REST Explorer project you can run it on an emulator or device.

1.	Run the project.

2.	Click the authentication account that you want to sign in to. > Note: As of the production of this sample application, only AAD (organizational) accounts will be able to make requests to the OneNote endpoints on the Microsoft Graph API. Support for MSA accounts is upcoming.

3.	Enter your credentials and sign in.

4.	Click a REST operation in the main activity to show operation details. >Note: Some operations require input before they will run. For example, to update a page, you must first select a page to update. On these operations there will be a spinner, or text box, to select or enter required input for an operation.

5.	Click the run button to start the REST operation and wait for the operation to finish.

6.	Click in the Response Headers or Response Body text boxes to copy the box contents to the emulator/device clipboard.

7.	Click the Back button on the REST Explorer toolbar to return to the REST operation list.

8.	(Optional) Click the overflow menu to get the Disconnect menu option.

Understand the code
-------------------

The REST API Explorer project uses these classes to manage interactions with OneNote for Enterprise and consumer OneNote resources:

### Sample project organization

The REST API explorer project is comprised of four modules. The modular design allows you to add authentication and OneNote REST API support to your app by importing modules from REST API Explorer into your app. After you've imported the modules, use the code in the REST API Explorer [app](https://github.com/OneNoteDev/Android-REST-API-Explorer/tree/master/app) module as an example of how to call methods in the other sample modules.

### REST API Explorer modules

-	[`O365-Auth`](https://github.com/OneNoteDev/Android-REST-API-Explorer/tree/master/O365-auth). This module contains the library calls to authenticate a user with Office 365.
-	[`onenoteapi`](https://github.com/OneNoteDev/Android-REST-API-Explorer/tree/master/onenoteapi). This module encapsulates the Retrofit REST operations used for the OneNote (enterprise and consumer) endpoints.
-	[`onenotevos`](https://github.com/OneNoteDev/Android-REST-API-Explorer/tree/master/onenotevos). This module provides the value objects that wrap deserialized JSON REST response payloads. Use the value objects in your app logic to get the metadata and content of OneDrive notebooks, sections, and pages returned by the API.
-	[`app`](https://github.com/OneNoteDev/Android-REST-API-Explorer/tree/master/app). The REST API explorer UI and business logic module. REST API Explorer consumes the **api** and **vo** modules from the logic in the app module. REST operations are started in the snippet classes in this module.

### Snippet classes

A snippet runs a single REST operation and returns the results. Snippets are found in the [app](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/tree/master/app) module. Snippets set the state required to make the calls on the OneNote service classes described below. Where necessary, a snippet class gets the notebooks, sections, or pages to load the spinner control shown on the snippet detail fragment for a given REST operation. 
- [`NotebookSnippet`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/app/src/main/java/com/microsoft/o365_android_onenote_rest/snippet/NotebookSnippet.java) 
- [`SectionGroupSnippet`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/app/src/main/java/com/microsoft/o365_android_onenote_rest/snippet/SectionGroupSnippet.java) 
- [`SectionSnippet`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/app/src/main/java/com/microsoft/o365_android_onenote_rest/snippet/SectionSnippet.java) 
- [`PagesSnippet`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/app/src/main/java/com/microsoft/o365_android_onenote_rest/snippet/PagesSnippet.java) 
- [`AbstractSnippet`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/app/src/main/java/com/microsoft/o365_android_onenote_rest/snippet/AbstractSnippet.java)

### OneNote API service classes

These classes are found in the [onenoteapi](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/tree/master/onenoteapi) module and make the Retrofit library calls that generate the REST queries and handle operation results. These service classes are consumed by the snippets. 
- [`NotebooksService`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenoteapi/src/main/java/com/microsoft/onenoteapi/service/NotebooksService.java) 
- [`SectionGroupsService`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenoteapi/src/main/java/com/microsoft/onenoteapi/service/SectionGroupsService.java) 
- [`SectionsService`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenoteapi/src/main/java/com/microsoft/onenoteapi/service/SectionsService.java) 
- [`PagesService`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenoteapi/src/main/java/com/microsoft/onenoteapi/service/PagesService.java)

### Value object classes

These classes are found in the [onenotevos](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/tree/master/onenotevos) module. The value object classes describe JSON payloads as objects.

-	[`BaseVO`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenotevos/src/main/java/com/microsoft/onenotevos/BaseVO.java). The superclass for other value objects.
-	[`Envelope`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenotevos/src/main/java/com/microsoft/onenotevos/Envelope.java). A collection of individual notebook, section, section group, or page objects returned in GET request.
-	[`Links`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenotevos/src/main/java/com/microsoft/onenotevos/Links.java). The collection of URLs returned in the body of a notebook, section, or page.
-	[`Notebook`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenotevos/src/main/java/com/microsoft/onenotevos/Notebook.java). A OneNote notebook.
-	[`Page`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenotevos/src/main/java/com/microsoft/onenotevos/Page.java). A OneNote page.
-	[`Section`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenotevos/src/main/java/com/microsoft/onenotevos/Section.java). A OneNote section.
-	[`SectionGroup`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/onenotevos/src/main/java/com/microsoft/onenotevos/SectionGroup.java). A OneNote section group.

### Authentication classes for Office 365 business accounts

The authentication classes are found in the [O365-Auth](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/tree/master/O365-auth) module. These classes use the [Microsoft Azure Active Directory Library (ADAL) for Android](https://github.com/AzureAD/azure-activedirectory-library-for-android) to connect to a business version of Office 365 such as Office 365 Enterprise.

-	[`AuthenticationManager`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/O365-auth/src/main/java/com/microsoft/AuthenticationManager.java). Encapsulates user connect and disconnect logic in addition to Azure app authorization.
-	[`AzureADModule`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/O365-auth/src/main/java/com/microsoft/AzureADModule.java). Authentication helper class.
-	[`AzureAppCompatActivity`](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/blob/master/O365-auth/src/main/java/com/microsoft/AzureAppCompatActivity.java). Dependency injection helper.

### Authentication for Office 365 personal accounts

Authentication for logging in with a Microsoft Account to a personal version of Office 365 such as Office 365 Home is handled by the [MSA Auth for Android](https://github.com/MSOpenTech/msa-auth-for-android) library. The app uses the [LiveAuthClient](https://github.com/MSOpenTech/msa-auth-for-android/blob/dev/src/main/java/com/microsoft/services/msa/LiveAuthClient.java) class to connect and disconnect.

### Screenshots

| Login                                     | REST Call List                                  |
|:-----------------------------------------:|:-----------------------------------------------:|
| ![Login screen](/readme-images/login.png) | ![REST call list page](/readme-images/list.png) |

| REST Call Detail                                    | Create Page                                    |
|:---------------------------------------------------:|:----------------------------------------------:|
| ![REST call detail page](/readme-images/detail.png) | ![Create Page](/readme-images/create_page.png) |

Questions and comments
----------------------

We'd love to get your feedback about the Microsoft Graph OneNote REST API Explorer for Android sample. You can send your feedback to us in the [Issues](https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer/issues) section of this repository. <br/> General questions about Office 365 development should be posted to [Stack Overflow](http://stackoverflow.com/questions/tagged/Office365+API). Make sure that your questions are tagged with [Office365] and [API].

Contributing
------------

You will need to sign a [Contributor License Agreement](https://cla.microsoft.com/) before submitting your pull request. To complete the Contributor License Agreement (CLA), you will need to submit a request via the form and then electronically sign the CLA when you receive the email containing the link to the document.

Additional resources
--------------------

-	Visit the [dev.onenote.com](http://dev.onenote.com) Dev Center
-	Contact us on [StackOverflow (tagged OneNote)](http://go.microsoft.com/fwlink/?LinkID=390182)
-	Follow us on [Twitter @onenotedev](http://www.twitter.com/onenotedev)
-	Read our [OneNote Developer blog](http://go.microsoft.com/fwlink/?LinkID=390183)
-	Explore the API using the [Graph Explorer](https://developer.microsoft.com/en-us/graph/graph-explorer)
-	[API Reference](https://developer.microsoft.com/en-us/graph/docs/api-reference/beta/resources/notes) documentation
-	[Known Issues](https://developer.microsoft.com/en-us/graph/docs/overview/release_notes)
-	[Getting Started](https://developer.microsoft.com/en-us/graph/docs/get-started/get-started) with the Microsoft Graph API

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/). For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.

Copyright
---------

Copyright (c) 2017 Microsoft. All rights reserved.
