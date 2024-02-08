package com.example.demo.entity;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "custom_id")
    private String customId;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "end_time")
    private Instant endTime;

    @ManyToOne
    @JoinColumn(name = "project_in_id")
    private Project projectIn;

    private String stage;

//    @Enumerated(EnumType.STRING)
//    private Priority priority;

    @Column(name = "blocked_by")
    private String blockedBy;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Ticket parent;

    @ManyToMany
    @JoinTable(
            name = "ticket_user",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> usersAssignedTo;

    @ElementCollection
    @CollectionTable(name = "ticket_custom_fields", joinColumns = @JoinColumn(name = "ticket_id"))
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value")
    private Map<String, Object> customFields;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "ticket_id")
//    private List<OtherLinks> otherLinks;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    public Ticket(RequestBodyTicket requestBodyTicket) {
//        this.id=  requestBodyTicket.getId();
//        this.customId=requestBodyTicket.getCustomId();
//        this.name = requestBodyTicket.getName();
//        this.description = requestBodyTicket.getDescription();
//        this.createdBy = new User("6555ad494d3b02f24de8200f");   //jay id
//        this.startDate = LocalDateTime.now();
//        this.blockedBy=requestBodyTicket.getBlockedBy();
//        this.endDate = requestBodyTicket.getEndDate();
//        this.endTime=Instant.parse(requestBodyTicket.getEndTime());
//        this.projectIn = new Project(requestBodyTicket.getProjectIn());
//        this.stage = requestBodyTicket.getStage();
//        this.priority = Priority.valueOf(requestBodyTicket.getPriority().toUpperCase());
//        this.customFields= requestBodyTicket.getCustomFields();
//        this.otherLinks= requestBodyTicket.getOtherLinks();
//        //this.blockedBy=
//        this.usersAssignedTo = requestBodyTicket.getUsersAssignedTo().stream()
//                .map(User::new)
//                .collect(Collectors.toList());
//    }

    public void generateCustomId(String projectInitials, long count) {
        this.customId = projectInitials + count;
    }
}
