package com.cmu.sigicmu.param.domaine;

public enum TableValeurTypeEnum {

    Pays("001"),
    TypeLocalite("002"),
    TypeDeDonnee("003"),
    Module("004"),
    RubriqParam("005"),
    TypeEntite("006"),
    TypeDocument("007"),
    FormatFichier("008"),
    Civilite("009"),
    Genre("010"),
    Fonction("011"),
    EtatCompte("012"),
    PoidsSignature("013"),
    NiveauConfidentialite("014"),
    ChampIntervention("015"),
    StatutMembre("016"),
    FormeJuridique("017"),
    RubriqTabBlord("018"),
    Unite("019"),
    Periodicite("020"),
    SourceDonnees("021"),
    TypeEntiteMutualiste("022"),
    EtatDrmandeAgrement("023"),
    EtatTraitDemandeAgrement("024"),
    PieceConstitutivesDossier("025"),
    NatureChangement("026"),
    TypeNote("027"),
    StatutMarital("028"),
    EtatDemandeImmatriculation("029"), 
    CategorieMutuelle("030"), 
    TypeOrganeAuSeinMutuelle("031"), 
    AnneeRecensement("032"), 
    Structure("033"),   
    ZoneGeographique("034"),  
    EtatSaisieDonnees("035"),
    HierarchiePyramideAdministrative("036"),
    NiveauCriticite("038"),
    EtatAlerte("039"),
    ObjetMutuelle("040"),
    FonctionOrgane("041"),
    ModeAcquisitionSiege("042"),
    GraphiqueIndicateur("046"),
    NatureStructureSante("047"),
    TypeStructureSante("048"),
    EtatFacture("060"),
    InitiativeFacture("061"),
    TypeFacture("062"),
    Service("063"),
    CategorieActe("064"),
    NiveauStructure("065"),
    EtatImportation("066"),
    NiveauControle("067"),
    ModePaiement("068"),
    ModeEnvoi("069"),
    UniteTemps("070"),
    NiveauValidationFacture("071"),
    MotifRejet("072"),
    TypeBeneficiaire("080"), // Adhérents et Bénéficiaires
    TypePiece("082"), // CNI, Passeport
    TypeAbsence("901"),
    EtatValidation("902"),
    NiveauValidation("904"),
    EtatAbsence("905"),
    NoteValidation("906"),
    
    
    CicuitValidation("078"),
    NiveauxValidation("077"),
    EtatValidationDesAction("076");
    
    
    
    private String code;

    private TableValeurTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
