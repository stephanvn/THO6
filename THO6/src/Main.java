import userinterface.MainFrame;
import application_logic.BusinessRuleControl;
import domain.BusinessRule;
import domain.BusinessRuleType;
import domain.Column;
import domain.Table;
import domain.Value;


public class Main {

	public static void main(String[] args) {
		BusinessRuleControl brc = BusinessRuleControl.getInstance();
		
		BusinessRule b1 = new BusinessRule("BRG_VBMG_PRT_CNS_ARNG_01");
		
		BusinessRuleType t1 = new BusinessRuleType("Attribute Range Rule","ARNG","The attribute value should or should not correspond to a value from a series of","xxx"); b1.setType(t1);	
		Table tab1 = new Table("Product"); 
		Column c1 = new Column("prijs"); tab1.addColumn(c1); b1.addTable(tab1);
		Value v1 = new Value("1"); b1.addValue(v1);
		Value v2 = new Value("2"); b1.addValue(v2);
		
		
		brc.addBusinessRule(b1);
		
		BusinessRule b2 = new BusinessRule("BRG_VBMG_PRT_CNS_ARNG_02");
		
		BusinessRuleType t2 = new BusinessRuleType("Attribute Range Rule","ARNG","The attribute value should or should not correspond to a value from a series of","xxx"); b2.setType(t2);	
		Table tab2 = new Table("Koek"); 
		Column c2 = new Column("prijs"); tab2.addColumn(c2); b2.addTable(tab2);
		Value v3 = new Value("1"); b2.addValue(v3);
		Value v4 = new Value("2"); b2.addValue(v4);
		
		brc.addBusinessRule(b2);
		
		BusinessRule b3 = new BusinessRule("BRG_VBMG_PRT_CNS_ARNG_03");
		
		BusinessRuleType t3 = new BusinessRuleType("Attribute Range Rule","ARNG","The attribute value should or should not correspond to a value from a series of","xxx"); b3.setType(t3);	
		Table tab3 = new Table("Pietjes"); 
		Column c3 = new Column("prijs"); tab3.addColumn(c3); b3.addTable(tab3);
		Value v5 = new Value("1"); b3.addValue(v5);
		Value v6 = new Value("2"); b3.addValue(v6);
		
		brc.addBusinessRule(b3);
		
		BusinessRule b4 = new BusinessRule("BRG_VBMG_PRT_CNS_ARNG_04");
		
		BusinessRuleType t4 = new BusinessRuleType("Attribute Range Rule","ARNG","The attribute value should or should not correspond to a value from a series of","xxx"); b4.setType(t4);	
		Table tab4 = new Table("Medewerkers"); 
		Column c4 = new Column("salaris"); tab4.addColumn(c4); b4.addTable(tab4);
		Value v7 = new Value("1"); b4.addValue(v7);
		Value v8 = new Value("2"); b4.addValue(v8);
		
		brc.addBusinessRule(b4);
		
		BusinessRule b5 = new BusinessRule("BRG_VBMG_PRT_CNS_ARNG_05");
		
		BusinessRuleType t5 = new BusinessRuleType("Attribute Range Rule","ARNG","The attribute value should or should not correspond to a value from a series of","xxx"); b5.setType(t5);	
		Table tab5 = new Table("Administrators"); 
		Column c5 = new Column("waarde"); tab5.addColumn(c5); b5.addTable(tab5);
		Value v9 = new Value("1"); b5.addValue(v9);
		Value v10 = new Value("2"); b5.addValue(v10);
		
		brc.addBusinessRule(b5);
		
		BusinessRule b6 = new BusinessRule("BRG_VBMG_PRT_CNS_TEST_06");
		
		BusinessRuleType t6 = new BusinessRuleType("Attribute Test Rule","TEST","The attribute value should or should not correspond to a value from a series of","xxx"); b6.setType(t6);	
		Table tab6 = new Table("Administrators"); 
		Column c6 = new Column("waarde"); tab5.addColumn(c6); b6.addTable(tab6);
		Value v11 = new Value("1"); b6.addValue(v11);
		Value v12 = new Value("2"); b6.addValue(v12);
		
		brc.addBusinessRule(b6);

		new MainFrame(brc);
	}

}
