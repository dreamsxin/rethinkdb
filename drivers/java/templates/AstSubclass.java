package com.rethinkdb.ast.gen;

import com.rethinkdb.ast.helper.Arguments;
import com.rethinkdb.ast.helper.OptArgs;
import com.rethinkdb.ast.RqlAst;
import com.rethinkdb.proto.TermType;
import java.util.*;

public class ${classname} extends ${superclass} {

% if term_type is not None:
    public ${classname}(RqlAst prev, Arguments args, OptArgs optargs) {
        this(prev, TermType.${term_type}, args, optargs);
    }
% endif
    protected ${classname}(RqlAst previous, TermType termType, Arguments args, OptArgs optargs){
        super(previous, termType, args, optargs);
    }
    /* Query level terms */
% for term, info in meta.iteritems():
    % if include_in in info.get('include_in', ['query']):
    public ${camel(term)} ${info.get('alias', dromedary(term))}(Object... fields) {
        return new ${camel(term)}(this, new Arguments(fields), new OptArgs());
    }

    % endif
% endfor
}