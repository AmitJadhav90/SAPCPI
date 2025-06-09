import com.sap.it.api.mapping.*;

def void assignValueByCondition(String[] conditionValues, String[] conditionString, String[] outputValues, Output output) {
    if(conditionValues != null && outputValues != null){
        def conditions = conditionString[0].split(";")

        conditionValues.eachWithIndex{conditionValue, idx ->
            def found = false
            for(condition in conditions){
                if(conditionValue.equalsIgnoreCase(condition)){
                    output.addValue(outputValues[idx])
                    found = true
                }
            }
            if(!found){
                output.addSuppress()
            }
        }
    }
}

def void createIfHasValue(String[] list, Output output) {
    if(list != null){
        list.each{ value ->
            if(value != null && !value.trim().isEmpty() && !output.isSuppress(value)){
                output.addValue("")
            }
            else{
                output.addSuppress()
            }
        }
    }
}

def void concatToOneQueue(String[] list1, String[] list2, String[] list3, String[] list4, String[] list5, Output output){
    if(list1 != null){
        list1.each { value ->  output.addValue(value) }
    } else {
        output.addSuppress()
    }

    if(list2 != null){
        list2.each { value ->  output.addValue(value) }
    } else {
        output.addSuppress()
    }

    if(list3 != null){
        list3.each { value ->  output.addValue(value) }
    } else {
        output.addSuppress()
    }

    if(list4 != null){
        list4.each { value ->  output.addValue(value) }
    } else {
        output.addSuppress()
    }

    if(list5 != null){
        list5.each { value ->  output.addValue(value) }
    } else {
        output.addSuppress()
    }
}

def void concatTwoQueuesToOne(String[] list1, String[] list2, Output output){
    if(list1 != null){
        list1.each { value ->
            output.addValue(value)
        }
    }
    if(list2 != null){
        list2.each { value ->
            output.addValue(value)
        }
    }
}

def void contextHasOneOfSuchValues(String[] list, String[] suchValuesString, Output output){
    if(list != null){
        def suchValues = suchValuesString[0].split(";")

        def oneOfSuchValues = "false"
        list.each{ value ->
            suchValues.each{ suchValue ->
                if(suchValue.equalsIgnoreCase(value)){
                    oneOfSuchValues = "true"
                }
            }
        }
        output.addValue(oneOfSuchValues)
    }
    else{
        output.addValue("false")
    }
}

def void deleteSuppress(String[] list, Output output){
    if(list != null){
        list.each { value ->
            if (value != null && !output.isSuppress(value)) {
                output.addValue(value)
            }
        }
    }
}

def void getFirstContextValue(String[] list, Output output){
    if(list != null){
        def found = false
        for(value in list){
            if (value != null && !output.isSuppress(value)) {
                output.addValue(value)
                found = true
                break
            }
        }
        if(!found){
            output.addSuppress()
        }
    }
}

def void getUniqueValues(String[] list, Output output){
    if(list != null){
        def unique_list = []
        list.each{ value ->
            if(output.isSuppress(value)){
                //Skip output Suppress
            }
            else if (!unique_list.contains(value)) {
                unique_list.add(value)
                output.addValue(value)
            } else {
                //Skip output Suppress
            }
        }
    }
}

def void getUniqueValuesWithSuppress(String[] list, Output output){
    if(list != null){
        def unique_list = []
        list.each{ value ->
            if(output.isSuppress(value)){
                output.addSuppress()
            }
            else if (!unique_list.contains(value)) {
                unique_list.add(value)
                output.addValue(value)
            } else {
                output.addSuppress()
            }
        }
    }
}

def void getValueByIndex(String[] list, String[] index, Output output){
    if(list != null){
        if(index != null){
            def idxStr = index[0]
            if(idxStr.isNumber()){
                def idx = idxStr.toInteger()
                if(idx <= list.size() && idx > 0){
                    output.addValue(list[idx-1])
                }
            }
        }
    }
}

def void hasOneOfSuchValues(String[] list, String[] suchValuesString, Output output){
    if(list != null){
        def suchValues = suchValuesString[0].split(";")

        list.each{ value ->
            def oneOfSuchValues = "false"
            suchValues.each{ suchValue ->
                if(suchValue.equalsIgnoreCase(value)){
                    oneOfSuchValues = "true"
                }
            }
            output.addValue(oneOfSuchValues)
        }
    }
    else{
        output.addValue("false")
    }
}

def void hasValue(String[] list, Output output) {
    if(list != null){
        list.each{ value ->
            if(value != null && !value.trim().isEmpty() && !output.isSuppress(value)){
                output.addValue("true")
            }
            else{
                output.addValue("false")
            }
        }
    }
    else{
        output.addValue("false")
    }
}

def void joinValuesToString(String[] list, String[] delimiterString, Output output){
    if(list != null){
        def out_list = []
        def delimiter = delimiterString[0]

        list.each { value ->
            out_list.add(value)
        }

        output.addValue(out_list.join(delimiter))
    }
}

def void passIfHasValue(String[] list, Output output) {
    if(list != null){
        list.each{ value ->
            if(value != null && !value.trim().isEmpty()){
    	        output.addValue(value)
    	    }
    	    else{
    	        output.addSuppress()
    	    }
        }
    }
}

def void splitStringToValues(String[] inputString, String[] delimiterString, Output output){
    if(inputString != null){
        def delimiter = delimiterString[0]

        inputString.each { line ->
            line.split(delimiter).each{ value ->
                output.addValue(value)
            }
        }
    }
}

def void useOneContextAsMany(String[] list, String[] secondList, Output output){
    if(list != null){
        if(secondList != null){
            secondList.eachWithIndex{value_second, idx_second ->

                list.each{ value ->
                    output.addValue(value)
                }

                //if not the last index of secondList
                if(idx_second != secondList.size()-1 ) {
                    output.addContextChange()
                }
                else{
                    //last context no need add context change
                }
            }
        }
    }
}