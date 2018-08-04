package com.objis.gestassociation.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import com.objis.gestassociation.dao.impl.EvenementDao;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.service.IEvenementService;

public class EvenementService implements IEvenementService {

    //les proprietes
    private EvenementDao dao = new EvenementDao();

    public EvenementService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Boolean create(Evenement t) {

        if (!(t.getId().equals(null))) {

            return dao.create(t);

        }

        return null;
    }

    @Override
    public Evenement readOne(Long pk) {

        if (!(pk.equals(null))) {

            return dao.readOne(pk);

        }

        return null;
    }

    @Override
    public Boolean update(Evenement t) {

        if (!(t.getId().equals(null))) {

            return dao.update(t);

        }

        return null;
    }

    @Override
    public Boolean delete(Long pk) {

        if (!(pk.equals(null))) {

            return dao.delete(pk);

        }

        return null;
    }

    @Override
    public List<Evenement> readAll() {

        return dao.readAll();

    }

    //methode permettant de retourner le nombre d'evenements
    public int nbEnregist() {

        return dao.readAll().size();

    }

    @Override
    public Double depensesParAnnee(String annee) {
        // TODO Auto-generated method stub

        LocalDate dateDebut = LocalDate.parse("01/01/" + annee, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dateFin = LocalDate.parse("31/12/" + annee, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return dao.depensesTtParAnnee(dateDebut, dateFin);
    }

    @Override
    public Double depensesParMois(int mois) {
        // TODO Auto-generated method stub

        int AnneeActuelle = Calendar.getInstance().getWeekYear();

        Double depnse = (double) 0;

//		System.out.println(mois);
        System.out.println(AnneeActuelle);

        for (Evenement evenement : readAll()) {

            if ((evenement.getDate().getYear() == AnneeActuelle) && (evenement.getDate().getMonthValue() == mois)) {
                depnse += evenement.getDepenseTt();
            }

        }

        return depnse;
    }

    @Override
    public Double depensesTT() {
        // TODO Auto-generated method stub
        return dao.depensesTt();
    }

}
