package com.ashpex;

public class SlangWord {
    private String slang;
    private String definition;

    SlangWord() {
        this.slang = "";
        this.definition = "";
    }
    public SlangWord(String slang, String definition) {
        this.slang = slang;
        this.definition = definition;
    }

    public String getSlang() {
        return this.slang;
    }

    public void setSlang(String slang) {
        this.slang = slang;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String addDefinition(String definition) {
        this.definition = this.definition + "|" + definition;
        return this.definition;
    }

    public String removeDefinition(String slang) {
        String str = "";
        if(this.definition.contains("|")) {
            String[] definitions = this.definition.split("\\|");
            for(String definition : definitions) {
                if(!definition.contains(slang)) {
                    str += definition + "|";
                }
            }
        }else {
            if(this.definition.contains(slang)) {
                str = "";
            }else {
                str = this.definition;
            }
        }
         return str;
    }

    public boolean containsDefinition(String str){
        return this.definition.contains(str);
    }

}



