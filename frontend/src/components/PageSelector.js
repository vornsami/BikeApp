import React from 'react'
import Grid from '@material-ui/core/Grid'
import Button from '@material-ui/core/Button'


const PageSelector = ({page, setPage}) => {
  return <Grid container item spacing={2}>
      {(page && page > 1 && <Grid item xs>
        <Button onClick= {() => {setPage(page-1)}}>← Previous Page</Button>
      </Grid>) || <Grid item xs/>}
      <Grid item xs>
        {page}
      </Grid>
      <Grid item xs>
        <Button onClick= {() => {setPage(page+1)}}>Next Page →</Button>
      </Grid>
    </Grid>
}

export default PageSelector