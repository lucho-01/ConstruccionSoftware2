package app.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "procedures")
public class ProcedureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderNumber;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false, length = 100)
    private String repetitionFrequency;

    @Column(nullable = false)
    private boolean specialistAssistance;

    @Column(nullable = false)
    private Long idTypeSpecialist;

    @Column(nullable = false)
    private int item;

    // === Getters y Setters ===
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRepetitionFrequency() {
        return repetitionFrequency;
    }
    public void setRepetitionFrequency(String repetitionFrequency) {
        this.repetitionFrequency = repetitionFrequency;
    }

    public boolean isSpecialistAssistance() {
        return specialistAssistance;
    }
    public void setSpecialistAssistance(boolean specialistAssistance) {
        this.specialistAssistance = specialistAssistance;
    }

    public Long getIdTypeSpecialist() {
        return idTypeSpecialist;
    }
    public void setIdTypeSpecialist(Long idTypeSpecialist) {
        this.idTypeSpecialist = idTypeSpecialist;
    }

    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
}
