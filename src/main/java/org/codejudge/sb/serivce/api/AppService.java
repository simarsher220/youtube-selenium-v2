package org.codejudge.sb.serivce.api;

import org.codejudge.sb.entity.Sentiment;
import org.codejudge.sb.error.CustomException;
import org.codejudge.sb.model.EvalRequest;

public interface AppService {
    public Sentiment initiate(EvalRequest request) throws CustomException;
    public Sentiment getSentiments(Integer id) throws CustomException;
}
