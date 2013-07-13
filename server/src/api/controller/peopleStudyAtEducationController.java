package api.controller;


import api.model.peopleStudyAtEducationResult;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class peopleStudyAtEducationController
{
  // Gets list of connections studying at schoolname as A JSON String
  public String getPeopleStudyAtEducation(String schoolname) {

    return peopleStudyAtEducationResult.returnConnections(schoolname);

  }

}
