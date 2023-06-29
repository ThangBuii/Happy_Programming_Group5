package com.hp.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mentor_skills")
@IdClass(MentorSkillCompositeKey.class)
public class Mentor_Skills {
    @Id
    @Column(name = "account_id")
    private int account_id;

    @Id
    @Column(name = "skill_id")
    private int skill_id;
}