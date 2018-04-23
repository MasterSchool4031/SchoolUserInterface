package school.client.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import school.client.commons.DataFactory;
import school.client.commons.Lesson;
import school.client.commons.School;
import school.client.commons.Training;
import school.client.commons.TrainingException;
import school.curriculum.Curriculum;

public class DataFactoryImpl implements DataFactory {
	
	private static DataFactoryImpl instance;
	private HashMap<String, SchoolImpl> schoolMap;

	private DataFactoryImpl() {
		super();
	}

	Lesson getNewLesson(String name)  throws TrainingException {
		return new LessonImpl(name);
	}
	
	Training getNewTraining(String schoolName, String trainingName,
			Curriculum lessonNamesList) throws TrainingException {
		return new TrainingImpl(schoolName, trainingName, lessonNamesList);
	}

	@Override
	public School getSchool(String schoolName,
			Map<String, Curriculum> curriculumMap) {
		SchoolImpl school = this.getSchoolMap().get(schoolName);
		if(school == null){
			List<Training> trainingList = new ArrayList<Training>();
			for( Entry<String, Curriculum> entry : curriculumMap.entrySet()){
				try {
					trainingList.add(this.getNewTraining(schoolName, entry
							.getKey(), entry.getValue()));
				} catch (TrainingException e) {
					// TODO getsion des messages d'erreur
					e.printStackTrace();
				}
			}
			school = new SchoolImpl(schoolName,trainingList);
			this.getSchoolMap().put(schoolName, school);
		}
		return school;
	}

	private HashMap<String, SchoolImpl> getSchoolMap() {
		if (schoolMap == null){
			this.schoolMap = new HashMap<String, SchoolImpl>();
		}
		return schoolMap;
	}

	public static DataFactoryImpl getInstance() {
		if (instance == null){
			instance = new DataFactoryImpl();
		}
		return instance;
	}

}
