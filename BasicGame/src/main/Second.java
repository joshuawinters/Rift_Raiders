package main;

public class Second {

    int x;
    int y;

    //sprites Shafir

    //shafir stappen naar onder
    static String imageShafirVoorkant1 = "Sprites\\Shafir\\ShafirVoorkant1.png";
    static String imageShafirVoorkant2 = "Sprites\\Shafir\\ShafirVoorkant2.png";

    //shafir stappen naar links
    static String imageShafirLinks1 = "Sprites\\Shafir\\ShafirLinks1.png";
    static String imageShafirLinks2 = "Sprites\\Shafir\\ShafirLinks2.png";

    //shafir stappen naar boven
    static String imageShafirAchterkant1 = "Sprites\\Shafir\\ShafirAchterkant1.png";
    static String imageShafirAchterkant2 = "Sprites\\Shafir\\ShafirAchterkant2.png";

    //shafir stappen naar rechts
    static String imageShafirRechts1 = "Sprites\\Shafir\\ShafirRechts1.png";
    static String imageShafirRechts2 = "Sprites\\Shafir\\ShafirRechts2.png";

    //Shafir idle sprite
    static String imageShafirIdle = "Sprites\\Shafir\\ShafirIdleVoor.png";
    //Stage Sprites
    static String imageStage = "Sprites\\Stage1.png";


//-------------------------------------------------------------------------------------------------------------------

    // enemy #1 sprites

    //enemy stappen naar onder
    static String imageCavemanOnder1 = "Sprites\\Enemy\\CavemanOnder1.png";
    static String imageCavemanOnder2 = "Sprites\\Enemy\\CavemanOnder2.png";

    //enemy stappen naar links
    static String imageCavemanLinks1 = "Sprites\\Enemy\\CavemanLinks1.png";
    static String imageCavemanLinks2 = "Sprites\\Enemy\\CavemanLinks2.png";

    //enemy stappen naar boven
    static String imageCavemanBoven1 = "Sprites\\Enemy\\CavemanBoven1.png";
    static String imageCavemanBoven2 = "Sprites\\Enemy\\CavemanBoven2.png";

    //enemy stappen naar rechts
    static String imageCavemanRechts1 = "Sprites\\Enemy\\CavemanRechts1.png";
    static String imageCavemanRechts2 = "Sprites\\Enemy\\CavemanRechts2.png";

    //enemy sprites idle
    static String imageCavemanIdle = "Sprites\\Enemy\\CavemanIdle.png";

    //Caveman attack sprites
    static String imageCavemanSlagLinks = "Sprites\\Enemy\\CavemanSlaanLinks.png";
    static String imageCavemanSlagRechts = "Sprites\\Enemy\\CavemanSlaanRechts.png";
    static String imageCavemanSlagVoor = "Sprites\\Enemy\\CavemanSlaanVoor.png";
    static String imageCavemanSlagAchter = "Sprites\\Enemy\\CavemanSlaanAchter.png";

    //caveman death sprites
    static String imageCavemanDeathVoor = "Sprites\\Enemy\\cavemanDeath\\cavemanDeathVoor.png";
    static String imageCavemanDeathAchter = "Sprites\\Enemy\\cavemanDeath\\cavemanDeathAchter.png";
    static String imageCavemanDeathLinks = "Sprites\\Enemy\\cavemanDeath\\cavemanDeathLinks.png";
    static String imageCavemanDeathRechts = "Sprites\\Enemy\\cavemanDeath\\cavemanDeathRechts.png";

    //---------------------------------------------------------------------------------------------------------------

    //Item & UI sprites

    //Hart boarder sprite
    static String imageHealthBoarder = "Sprites\\UI&Items\\HealthBoarder1.png";
    //Hart sprites
    static String imageHartVol1 = "Sprites\\UI&Items\\HeartFull1.png";
    static String imageHartVol2 = "Sprites\\UI&Items\\HeartFull2.png";
    static String imageHartVol3 = "Sprites\\UI&Items\\HeartFull3.png";
    static String imageHartLeeg1 = "Sprites\\UI&Items\\HeartEmpty1.png";
    static String imageHartLeeg2 = "Sprites\\UI&Items\\HeartEmpty2.png";
    static String imageHartLeeg3 = "Sprites\\UI&Items\\HeartEmpty3.png";

    //Knuppel sprite
    static String imageKnuppel = "Sprites\\UI&Items\\Knuppel.png";

    //gameover screen
    static String imageGameoverScreen = "Sprites\\gameOverScreen.png";

    //gamecompleted screen
    static String imageCompletedScreen = "Sprites\\Congratiulations.png";

    //main boss hoofd voor dialoge
    static String imageMainBHoofd = "Sprites\\mainBoss1\\mainBossHoofd.png";

    //main boss health bar
    static String imageHealthBossVol = "Sprites\\UI&Items\\bossHealthVol.png";
    static String imageHealthBoss1Hit = "Sprites\\UI&Items\\bossHealth4.5.png";
    static String imageHealthBoss2Hit = "Sprites\\UI&Items\\bossHealth3.5.png";
    static String imageHealthBoss3Hit = "Sprites\\UI&Items\\bossHealth2.5.png";
    static String imageHealthBoss4Hit = "Sprites\\UI&Items\\bossHealth1.5.png";
    static String imageHealthBossLeeg = "Sprites\\UI&Items\\bossHealthLeeg.png";





    //---------------------------------------------------------------------------------------------------------------

    //shaifr emote
    static String imageShafirEmote = "Sprites\\Shafir\\shafirDuim.png";


    //Shafir met knuppel sprites
    static String shafirKnuppelBoven1 = "Sprites\\Shafir\\ShafirMetKnuppel\\McKNuppelBoven1.png";
    static String shafirKnuppelBoven2 = "Sprites\\Shafir\\ShafirMetKnuppel\\McKNuppelBoven2.png";
    static String shafirKnuppelOnder1 = "Sprites\\Shafir\\ShafirMetKnuppel\\McKNuppelOnder1.png";
    static String shafirKnuppelOnder2 = "Sprites\\Shafir\\ShafirMetKnuppel\\McKNuppelOnder2.png";
    static String shafirKnuppelLinks1 = "Sprites\\Shafir\\ShafirMetKnuppel\\McKNuppelLinks1.png";
    static String shafirKnuppelLinks2 = "Sprites\\Shafir\\ShafirMetKnuppel\\McKNuppelLinks2.png";
    static String shafirKnuppelRechts1 = "Sprites\\Shafir\\ShafirMetKnuppel\\McKNuppelRechts1.png";
    static String shafirKnuppelRechts2 = "Sprites\\Shafir\\ShafirMetKnuppel\\McKNuppelRechts2.png";

    //Shafir slag animatie
    static String imageShafirSlagLinks = "Sprites\\Shafir\\ShafirMetKnuppel\\ShafirSlaatLinks.png";
    static String imageShafirSlagRechts = "Sprites\\Shafir\\ShafirMetKnuppel\\ShafirSlaatRechts.png";
    static String imageShafirSlagOnder = "Sprites\\Shafir\\ShafirMetKnuppel\\ShafirSlaatOnder.png";
    static String imageShafirSlagBoven = "Sprites\\Shafir\\ShafirMetKnuppel\\ShafirSlaatBoven.png";

    //Shafir met damage animatie
    static String imageShafirDamageVoor = "Sprites\\Shafir\\ShafirMetDamage\\ShafirMetDamageVoor.png";
    static String imageShafirDamageAchter = "Sprites\\Shafir\\ShafirMetDamage\\ShafirMetDamageAchter.png";
    static String imageShafirDamageLinks = "Sprites\\Shafir\\ShafirMetDamage\\ShafirMetDamageLinks.png";
    static String imageShafirDamageRechts = "Sprites\\Shafir\\ShafirMetDamage\\ShafirMetDamageRechts.png";

    //shafir death animation
    static String imageShafirDeathAchter = "Sprites\\Shafir\\ShafirDeath\\ShafirDoodAchter.png";
    static String imageShafirDeathVoor = "Sprites\\Shafir\\ShafirDeath\\ShafirDoodVoor.png";
    static String imageShafirDeathLinks = "Sprites\\Shafir\\ShafirDeath\\ShafirDoodLinks.png";
    static String imageShafirDeathRechts = "Sprites\\Shafir\\ShafirDeath\\ShafirDoodRechts.png";

    //---------------------------------------------------------------------------------------------------------------

    //main boss sprites

    //main boss idle
    static String imageBossIdle = "Sprites\\mainBoss1\\mainBossIdle.png";

    //main boss stappen
    static String imageBStapBoven1 = "Sprites\\mainBoss1\\mainBStapBoven1.png";
    static String imageBStapBoven2 = "Sprites\\mainBoss1\\mainBStapBoven2.png";
    static String imageBStapOnder1 = "Sprites\\mainBoss1\\mainBStapOnder1.png";
    static String imageBStapOnder2 = "Sprites\\mainBoss1\\mainBStapOnder2.png";
    static String imageBStapLinks1 = "Sprites\\mainBoss1\\mainBStapLinks1.png";
    static String imageBStapLinks2 = "Sprites\\mainBoss1\\mainBStapLinks2.png";
    static String imageBStapRechts1 = "Sprites\\mainBoss1\\mainBStapRechts1.png";
    static String imageBStapRechts2 = "Sprites\\mainBoss1\\mainBStapRechts2.png";

    //main boss sprites stappen met wapen
    static String imageBStapWapenVoor1 = "Sprites\\mainBoss1\\mainBossAttack\\mainBossWapen\\mainBStapWapenVoor1.png";
    static String imageBStapWapenVoor2 = "Sprites\\mainBoss1\\mainBossAttack\\mainBossWapen\\mainBStapWapenVoor2.png";
    static String imageBStapWapenAchter1 = "Sprites\\mainBoss1\\mainBossAttack\\mainBossWapen\\mainBStapWapenAchter1.png";
    static String imageBStapWapenAchter2 = "Sprites\\mainBoss1\\mainBossAttack\\mainBossWapen\\mainBStapWapenAchter2.png";
    static String imageBStapWapenLinks1 = "Sprites\\mainBoss1\\mainBossAttack\\mainBossWapen\\mainBStapWapenLinks1.png";
    static String imageBStapWapenLinks2 = "Sprites\\mainBoss1\\mainBossAttack\\mainBossWapen\\mainBStapWapenLinks2.png";
    static String imageBStapWapenRechts1 = "Sprites\\mainBoss1\\mainBossAttack\\mainBossWapen\\mainBStapWapenRechts1.png";
    static String imageBStapWapenRechts2 = "Sprites\\mainBoss1\\mainBossAttack\\mainBossWapen\\mainBStapWapenRechts2.png";

    //main boss hold sprites
    static String imageMainBHoldVoor = "Sprites\\mainBoss1\\mainBossAttack\\mainBossHoldVoor.png";
    static String imageMainBHoldAchter = "Sprites\\mainBoss1\\mainBossAttack\\mainBossHoldAchter.png";
    static String imageMainBHoldLinks = "Sprites\\mainBoss1\\mainBossAttack\\mainBossHoldLinks.png";
    static String imageMainBHoldRechts = "Sprites\\mainBoss1\\mainBossAttack\\mainBossHoldRechts.png";

    //main boss attack sprites
    static String imageBossAttackVoor = "Sprites\\mainBoss1\\mainBossAttack\\mainBossAttackVoor.png";
    static String imageBossAttackAchter = "Sprites\\mainBoss1\\mainBossAttack\\mainBossAttackAchter.png";
    static String imageBossAttackLinks = "Sprites\\mainBoss1\\mainBossAttack\\mainBossAttackLinks.png";
    static String imageBossAttackRechts = "Sprites\\mainBoss1\\mainBossAttack\\mainBossAttackRechts.png";















}
