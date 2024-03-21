package com.accountable.service;


import com.accountable.entity.Classroom;
import com.accountable.entity.Organization;
import com.accountable.entity.User;
import com.accountable.exception.ErrorCode;
import com.accountable.exception.GenericException;
import com.accountable.repository.ClassroomRepository;
import com.accountable.repository.OrganizationRepository;
import com.accountable.repository.UserRepository;
import com.accountable.utilities.EntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassroomService {
    private final ClassroomRepository classroomRepo;
    public Classroom create(UUID orgId, Classroom classroom){
        if (null != orgId){
            classroom.setOrgId(orgId);
        }
        Classroom newClass =  classroomRepo.saveAndFlush(classroom);
        if (null == newClass.getCode()){
            // Class code is the first 7 digits of UUID (which is not unique)
            newClass.setCode(newClass.getId().toString().substring(0, 7));
            classroomRepo.saveAndFlush(newClass);
        }
        return newClass;
    }

    public Classroom get(UUID classroomId){
        return classroomRepo.getClassroomByIdAndIsActiveTrue(classroomId);
    }


    public List<Classroom> getClassroomsByUserId(UUID userId){
        // use userClassMappingRepo to get this
        // select * from Classroom as c
        return null;
    }


    public Classroom update(Classroom classroom){
        UUID classroomId = classroom.getId();
        Classroom existingClassroom = get(classroomId);
        if (null == existingClassroom){
            throw new GenericException(ErrorCode.CLASSROOM_ON_UPDATE, "classroom does not exist");
        }

        Classroom patchedOrganization = EntityUtils.mergeEntitiesDelta(existingClassroom, classroom);
        return classroomRepo.saveAndFlush(patchedOrganization);
    }

    // TODO: Consider make isActive false to preserve user data instead of hard delete
    public Classroom delete(UUID classroomId){
        Classroom existingClassroom = get(classroomId);
        if (null == existingClassroom){
            throw new GenericException(ErrorCode.CLASSROOM_ON_UPDATE, "classroom does not exist");
        }
        classroomRepo.delete(existingClassroom);
        return existingClassroom;
    }
}
